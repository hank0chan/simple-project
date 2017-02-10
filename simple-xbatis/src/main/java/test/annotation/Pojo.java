package test.annotation;

import test.Dao;
import test.PojoRefSpringBeanUtil;

public class Pojo {

	@PojoRefBean(Dao.class)
	Dao dao;
	Dao dao2 = PojoRefSpringBeanUtil.getBean(Dao.class);  // 可行的
	
	public void pojo() {
		System.out.println("pojo...");
		System.out.println("@PojoRefBean: " + dao);
		dao2.dao();
	}
	
}
