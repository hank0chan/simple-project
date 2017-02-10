package xbatis.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xbatis.base.Criteria;
import xbatis.data.sample.entity.Sample;
import xbatis.data.sample.service.SampleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestSampleMVC {

	@Autowired
	SampleService sampleService;
	
	@Test
	public void test() {
		Sample sample = sampleService.get(Criteria.createCriteria().with("firstAttr", "111"));
		System.out.println(sample);
	}
}
