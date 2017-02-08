package xbatis.datasource;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestDataSource {

	@Autowired
	DruidDataSource dataSource;
	@Autowired
	DataSource c3p0DataSource;
	@Autowired
	DataSource dbcpDataSource;
	
	@Test
	public void test() {
		System.out.println("druidDataSource: " + dataSource);
		System.out.println("c3p0DataSource: " + c3p0DataSource);
		System.out.println("dbcpDataSource: " + dbcpDataSource);
	}
}
