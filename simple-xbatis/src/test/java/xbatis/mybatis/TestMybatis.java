package xbatis.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestMybatis {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void test() {
		System.out.println(sqlSessionFactory);
	}
}
