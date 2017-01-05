package swift.dao.drds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 测试使用DRUID数据源操作DRDS
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 17:09:45 - 4 Jan 2017
 * @detail 只用于测试，SQL操作使用了最原始的JDBC开发方式
 */
public class TestDruidDataSource {
	
	ApplicationContext xml;
	DruidDataSource dataSource;
	Connection conn;
	/**
	 * 通过XML配置方式初始化Spring；获取DataSource
	 * @throws SQLException 
	 */
	@Before
	public void init() throws SQLException {
		xml = new ClassPathXmlApplicationContext("classpath:application-ali-druid.xml");
		dataSource = (DruidDataSource) xml.getBean("dataSource");
		conn = dataSource.getConnection();
	}
	/**
	 * 关闭资源
	 */
	@After
	public void destory() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除数据
	 */
	@Test
	public void testDelete() {
		String sql = "DELETE FROM ts_station WHERE AreaCode = '广州' AND CityCode = '广州'";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				System.out.println("Delete Success: [ " + sql + " ]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 插入数据
	 */
	@Test
	public void testInsert() {
		String sql1 = "INSERT INTO `ts_station` VALUES ('G8002', '怀集县大岗镇大岗镇东岗路', '112.040', '23.870', '2', '9999.000', '441224', '广东省', '怀集', '肇庆')";
		String sql2 = "INSERT INTO `ts_station` VALUES ('G8003', '怀集县下帅镇下帅圩', '112.080', '24.210', '2', '9999.000', '441224', '广东省', '怀集', '肇庆')";
		String sql3 = "INSERT INTO `ts_station` VALUES ('G8004', '怀集县梁村镇梁村圩', '112.039', '23.918', '2', '9999.000', '441224', '广东省', '怀集', '肇庆')";
		String sql4 = "INSERT INTO `ts_station` VALUES ('G8005', '怀集县坳仔镇坳仔圩', '112.290', '23.840', '2', '9999.000', '441224', '广东省', '怀集', '肇庆')";
		String sql5 = "INSERT INTO `ts_station` VALUES ('G8006', '怀集县永固镇永固圩', '112.110', '23.730', '2', '9999.000', '441224', '广东省', '怀集', '肇庆')";
		List<String> sqls = Arrays.asList(sql1, sql2, sql3, sql4, sql5);
		
		PreparedStatement preparedStatement = null;
		try {
			
			for(String sql : sqls) {
				preparedStatement = conn.prepareStatement(sql);
				int result = preparedStatement.executeUpdate();				
				if(result > 0) {
					System.out.println("Update Success: [ " + sql + " ]");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 从DRDS数据库获取数据
	 */
	@Test
	public void testGet() {
		String sql = "SELECT StationID, Name, CountyCode, AreaCode, CityCode FROM ts_station WHERE CityCode = '广州'";
		List<Map<String, String>> lists = new ArrayList<>();  // 输出结果集合
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Map<String, String> map = new HashMap<>();
	            String stationID = resultSet.getString("StationID");
	            String name = resultSet.getString("Name");
	            String countyCode = resultSet.getString("CountyCode");
	            String areaCode = resultSet.getString("AreaCode");
	            String cityCode = resultSet.getString("CityCode");
	            map.put("stationID", stationID);
	            map.put("name", name);
	            map.put("countyCode", countyCode);
	            map.put("areaCode", areaCode);
	            map.put("cityCode", cityCode);
	            lists.add(map);
	        }
			for(Map<String, String> list : lists) {
				System.out.println("Success to Get: " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 获取数据源
	 */
	@Test
	public void testDataSource() {
		DruidDataSource dataSource = (DruidDataSource) xml.getBean("dataSource");
		System.out.println(dataSource.toString());
	}
}
