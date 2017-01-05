package swift.dao.drds;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import swift.dao.drds.demo.DRDSServiceDemo;
import swift.dao.drds.entity.StationEntity;

public class TestServiceDemo {

	@Test
	public void test() {
		@SuppressWarnings("resource")
		ApplicationContext xml = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		DRDSServiceDemo drdsServiceDemo = (DRDSServiceDemo) xml.getBean("drdsServiceDemo");
		StationEntity entity = drdsServiceDemo.getSationByCityCode("广州");
		System.out.println(entity);
	}
}
