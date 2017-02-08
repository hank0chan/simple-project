package xbatis.base;

import java.util.HashMap;

@SuppressWarnings({ "serial" })
public class Criteria extends HashMap<String, Object> {

	public static Criteria createCriteria() {
		return new Criteria();
	}
	
	private Criteria() { }
	
	public Criteria with(String key, Object value) {
		put(key, value);
		return this;
	}

}
