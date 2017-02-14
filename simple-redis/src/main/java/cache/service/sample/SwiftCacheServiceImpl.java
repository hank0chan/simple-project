package cache.service.sample;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cache.redis.JedisUtils;
import cache.service.SwiftCacheService;

/**
 * 尝试Redis缓存服务的第一种实现
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 18:00:44 - 13 Feb 2017
 * @detail
 */
@Service
public class SwiftCacheServiceImpl implements SwiftCacheService {
	
	@Override
	public Serializable get(String cacheName, String cacheKey) {
		return JedisUtils.get(cacheKey);
	}

	@Override
	public void put(String cacheName, int expire, String cacheKey, Serializable result) {
		// expire是缓存时效，单位：秒
		// TODO 加入缓存时效
		JedisUtils.put(cacheKey, result, expire);
	}

}
