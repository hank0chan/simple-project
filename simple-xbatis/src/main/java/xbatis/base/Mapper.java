package xbatis.base;

import java.util.List;

public interface Mapper<T> {

	public int create(T object);

	public T get(Object criteria);

	public int count(Object criteria);

	public List<T> query(Object criteria);

	public int update(T object);

	public int delete(Object criteria);

}
