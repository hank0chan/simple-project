package cache.annotation;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class DataDao {

	@SwiftCache(expire=60)
	public DataEntity get(String firstAttr) {
		DataEntity dataEntity = new DataEntity();
		try {
			// 模拟数据库取数据耗时
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dataEntity.setFirstAttr(firstAttr + ":" + new Date().toString());
		return dataEntity;
	}
}
