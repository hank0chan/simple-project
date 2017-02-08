package xbatis.mybatis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xbatis.base.Criteria;
import xbatis.data.sample.dao.SampleRepository;
import xbatis.data.sample.entity.Sample;

public class TestSampleRepository {

	ApplicationContext xml;
	SampleRepository sampleRepository;
	
	@Before
	public void init() {
		xml = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		sampleRepository = (SampleRepository) xml.getBean("sampleRepository");
	}
	
	@Test
	public void test2() {
		Sample sample = sampleRepository.businessQuery(Criteria.createCriteria().with("firstAttr", "111"));
		System.out.println(sample);
	}
	
	@Test
	public void test() {
		System.out.println(sampleRepository);
	}
}
