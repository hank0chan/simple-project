package xbatis.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xbatis.base.Criteria;
import xbatis.data.sample.entity.Sample;
import xbatis.data.sample.mapper.SampleMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestSampleMapper {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void test() {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			SampleMapper mapper = sqlSession.getMapper(SampleMapper.class);
			Sample sample = mapper.get(Criteria.createCriteria().with("firstAttr", "111"));
			System.out.println(sample);
		}
	}
}
