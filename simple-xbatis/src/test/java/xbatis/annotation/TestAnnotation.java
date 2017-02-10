package xbatis.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.annotation.Pojo;
import test.annotation.PojoRefBeanAnnotationCovertor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestAnnotation {

	@Autowired
	PojoRefBeanAnnotationCovertor annotationCovertor;
	
	@Test
	public void test() {
		Pojo pojo = new Pojo();
		pojo.pojo();
	}
}
