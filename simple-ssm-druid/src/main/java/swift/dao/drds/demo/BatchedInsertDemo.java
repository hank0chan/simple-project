package swift.dao.drds.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 官方提供的批量数据导入的Demo示例
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 16:31:36 - 5 Jan 2017
 * @detail
 */
public class BatchedInsertDemo {

	@SuppressWarnings("resource")
	public void connect() throws SQLException {
		//连接设置和创建
		ApplicationContext xml = new ClassPathXmlApplicationContext("classpath:application-ali-druid.xml");
		DruidDataSource dataSource = (DruidDataSource) xml.getBean("dataSource");
		Connection conn = dataSource.getConnection();
		// 导入数据
		doBatchedInsert(conn, 2, 2);
	}
	
	public static void doBatchedInsert(Connection conn, int batchSize, int insertCount) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("insert into Test (name,gmt_created,gmt_modified) values (?,now(),now())");
		for (int i = 0; i < insertCount; i++) {
			ps.setString(1, i + " ");
			ps.addBatch();
			if ((i + 1) % batchSize == 0) {
				ps.executeBatch();
			}
		}
		ps.executeBatch();
		ps.close();
	}
}
