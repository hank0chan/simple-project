package cache.service;

import java.io.Serializable;

/**
 * 缓存服务接口，需要自定义实现该接口，为自定义缓存@SwiftCache服务
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 17:03:52 - 13 Feb 2017
 * @detail 针对多种实现，可以用于后面的Redis集群的缓存服务实现
 */
public interface SwiftCacheService {

	/**
	 * 获取缓存
	 * @param cacheName 缓存名
	 * @param cacheKey 缓存键
	 * @return
	 */
	public Serializable get(String cacheName, String cacheKey);

	/**
	 * 加入缓存
	 * @param cacheName 缓存名
	 * @param expire 缓存时效，单位：秒
	 * @param cacheKey 缓存键
	 * @param result 缓存内容
	 */
	public void put(String cacheName, int expire, String cacheKey, Serializable result);

}
