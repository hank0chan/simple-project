package xbatis.base;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Mybatis基础类
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 17:37:02 - 8 Jan 2017
 * @detail Mybatis整合Spring作为DAO层，在Spring配置文件配置使用Mybatis自定义Mapper包扫描器；
 * 使用DRUID作为数据源，DRDS作为分布式数据库中间件。
 */
public abstract class AbstractRepository {
	
	public static final String SUFFIX_MAPPER = "Mapper";
	public static final String MAPPER_CREATE = "create";  // 映射MapperXml文件的statementId
	public static final String MAPPER_COUNT = "count";    // 映射MapperXml文件的statementId
	public static final String MAPPER_QUERY = "query";    // 映射MapperXml文件的statementId
	public static final String MAPPER_UPDATE = "update";  // 映射MapperXml文件的statementId
	public static final String MAPPER_DELETE = "delete";  // 映射MapperXml文件的statementId
	public static final String MAPPER_GET = "get";        // 映射MapperXml文件的statementId
	
	/** Mapper接口的包名 */
	protected String mappersPackage;
	public void setMappersPackage(String mappersPackage) {
		this.mappersPackage = mappersPackage + ".";
	}
	protected SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/** 测试用 */
	protected DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public <T> Object execute(Class<T> resultClass, Excecution excecution) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Object mapper = session.getMapper(Class.forName(mappersPackage + resultClass.getSimpleName() + SUFFIX_MAPPER));
			if(mapper == null) throw new NullPointerException("mapper == null");
			Object result = excecution.apply(mapper);
			session.commit();
			return result;
		} catch(Exception x) {
			session.rollback();
			throw new RuntimeException(x);
		} finally {
			session.close();
		}
	}
	
	/**
	 * 新增记录
	 * @param object 新增对象
	 * @return
	 */
	public <T> T create(T object) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			int n = session.insert(mappersPackage + object.getClass().getSimpleName() + SUFFIX_MAPPER + "." + MAPPER_CREATE, object);
			return n > 0 ? object : null;
		}
	}
	
	/**
	 * 查询一条记录
	 * @param verb Mapper XML中SQL语句对应的mapperStatementID，如：get
	 * @param resultClass 实体类
	 * @param criteria 输入参数
	 * @return
	 */
	public <T> T get(String verb, Class<T> resultClass, Object criteria) {	
		try(SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(mappersPackage + resultClass.getSimpleName() + SUFFIX_MAPPER + "." + verb, criteria);
		}
	}

	/**
	 * 查询一条记录
	 * @param resultClass 实体类
	 * @param criteria 输入参数
	 * @return
	 */
	public <T> T get(Class<T> resultClass, Object criteria) {
		return get(MAPPER_GET, resultClass, criteria);
	}

	/**
	 * 统计
	 * @param resultClass 实体类
	 * @param criteria 输入参数
	 * @return
	 */
	public <T> int count(Class<T> resultClass, Object criteria) {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(mappersPackage + resultClass.getSimpleName() + SUFFIX_MAPPER + "." + MAPPER_COUNT, criteria);
		}
	}

	/**
	 * 查询多条记录
	 * @param verb Mapper XML中SQL语句对应的mapperStatementID，如：get
	 * @param resultClass 实体类
	 * @param criteria 输入参数
	 * @return
	 */
	public <T> List<T> query(String verb, Class<T> resultClass, Object criteria) {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectList(mappersPackage + resultClass.getSimpleName() + SUFFIX_MAPPER + "." + verb, criteria);
		}
	}

	/**
	 * 查询多条记录
	 * @param resultClass 实体类
	 * @param criteria 输入参数
	 * @return
	 */
	public <T> List<T> query(Class<T> resultClass, Object criteria) {
		return query(MAPPER_QUERY, resultClass, criteria);
	}

	public <T> void query(String verb, Class<T> resultClass, Object criteria, final RowHandler<T> handler) {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			session.select(mappersPackage + resultClass.getSimpleName() + SUFFIX_MAPPER + "." + verb, criteria, new ResultHandler<T>() {
				@Override
				public void handleResult(ResultContext<? extends T> resultContext) {
					handler.handle(resultContext.getResultObject());
				}
			});
		}
	}

	public <T> void query(Class<T> resultClass, Object criteria, RowHandler<T> handler) {
		query(MAPPER_QUERY, resultClass, criteria, handler);
	}

	/**
	 * 检索操作
	 * @param resultClass 实体类
	 * @param criteria 输入参数
	 * @return
	 */
	public <T> ResultSet<T> retrieve(Class<T> resultClass, Object criteria) {
		SqlSession session = sqlSessionFactory.openSession();
		Cursor<T> cursor = session.selectCursor(mappersPackage + resultClass.getSimpleName() + SUFFIX_MAPPER + "." + MAPPER_QUERY, criteria);
		return new ResultSet<T>(session, cursor);
	}

	/**
	 * 更新操作
	 * @param object 更新对象实体类
	 * @return
	 */
	public <T> T update(T object) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			int n = session.update(mappersPackage + object.getClass().getSimpleName() + SUFFIX_MAPPER + "." + MAPPER_UPDATE, object);
			return n > 0 ? object : null;
		}
	}

	/**
	 * 删除操作
	 * @param resultClass 实体类
	 * @param criteria 输入参数集合
	 * @return
	 */
	public <T> int delete(Class<T> resultClass, Object criteria) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.delete(mappersPackage + resultClass.getSimpleName() + SUFFIX_MAPPER + "." + MAPPER_DELETE, criteria);
		}
	}

}
