package swift.dao.drds.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import swift.dao.drds.entity.StationEntity;
import swift.dao.drds.mapper.StationEntityMapper;

/**
 * Service测试类
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 16:32:11 - 5 Jan 2017
 * @detail
 */
public class DRDSServiceDemo {
	
	/**
	 * 使用Setter注入方式注入SqlSessionFactory的Bean
	 */
	SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public StationEntity getSationByCityCode(String cityCode) {
		StationEntity entity = new StationEntity();
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			StationEntityMapper mapper = sqlSession.getMapper(StationEntityMapper.class);
			entity = mapper.getStationByCity("广州");
		}
		return entity;
	}
}
