package swift.dao.drds;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Optional;

import swift.dao.drds.entity.StationEntity;
import swift.dao.drds.mapper.StationEntityMapper;

/**
 * 测试通过Mybatis操作DRDS
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 13:43:52 - 5 Jan 2017
 * @detail 使用Mybatis作为ORM框架测试操作DRDS，后续可以轻松接入原来的swift-edas项目
 */
public class TestMybatis {
	
	ApplicationContext xml;
	SqlSessionFactory sqlSessionFactory;
	/**
	 * 通过XML配置方式初始化Spring；获取DataSource
	 * @throws SQLException 
	 */
	@Before
	public void init() throws SQLException {
		xml = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		sqlSessionFactory = (SqlSessionFactory) xml.getBean("sqlSessionFactory");
	}
	/**
	 * 关闭资源
	 */
	@After
	public void destory() {	}
	
	/**
	 * 测试更新数据
	 */
	@Test
	public void testUpdate() {
		try(SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			StationEntity entity = new StationEntity();
			entity.setStationId("G8006");
			entity.setName("Test:怀集县-永固镇-永固圩");
			System.out.println(entity);
			StationEntityMapper mapper = sqlSession.getMapper(StationEntityMapper.class);
			int result = mapper.update(entity);
			if(result > 0) {
				System.out.println("Update Success: " + entity);
			} else {
				System.out.println("Failure..");
			}
		}
	}
	/**
	 * 测试获取多条数据：采用SqlSession的Mapper接口方法（建议使用，面向接口，并且类型安全）
	 */
	@Test
	public void testQueryList() {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			List<StationEntity> stations = new ArrayList<>();
			StationEntityMapper mapper = sqlSession.getMapper(StationEntityMapper.class);
			stations = mapper.queryStationsByCity("广州");
			if(Optional.fromNullable(stations).isPresent()) {
				for(StationEntity station : stations) {
					System.out.println("Success to Get: " + station);
				}
			} else {
				System.out.println("Failure...");
			}
		}
	}
	/**
	 * 测试获取一条数据：使用原始的SqlSession对象的方法（缺点是类型不安全）
	 */
	@Test
	public void testGetOne() {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			StationEntity station = new StationEntity();
			station = (StationEntity) sqlSession.selectOne(
					"swift.dao.drds.mapper.StationEntityMapper.getStationByCity", "广州");
			System.out.println(station);
		}
	}
	/**
	 * 测试能否正常启动Mybatis（即Spring整合Mybatis是否成功）
	 */
	@Test
	public void testInit() {
		System.out.println(sqlSessionFactory);
		DataSource dataSource = (DataSource) xml.getBean("dataSource");
		System.out.println(dataSource);
	}
}
