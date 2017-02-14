package cache.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-redis.xml"})
public class SwiftCacheAnnotationTest {

	@Autowired
	DataService dataService;
	
	@Test
	public void test() {
		long start = System.currentTimeMillis();
		System.out.println(dataService.get("hello"));
		long end = System.currentTimeMillis();
		System.out.println("消耗：" + (end - start) + "ms");
	}
}
