package test;

public class Singleton {

	Dao dao = (Dao) PojoRefSpringBeanUtil.getBean("dao");
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static Singleton instance = new Singleton(); 
	}
	
	public void print() {
		System.out.println("hello..........");
		System.out.println(dao);
		dao.dao();
	}
	
}
