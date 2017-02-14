package cache.annotation;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cache.service.SwiftCacheService;

/**
 * 方法级别的缓存拦截器切面
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 16:57:13 - 13 Feb 2017
 * @detail 本切面代理了SwiftCache注解的实现。给被该注解SwiftCache修饰的方法加入缓存实现。
 * 就是说，在调用被修饰的方法前会查询缓存信息，存在则直接返回缓存信息，结束方法。否则，执行被修饰的方法。
 */
@Aspect
@Component
public class SwiftCacheAnnotationAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger("SWIFT");
	private static final String CACHE_NAME = "SWIFT_ANNOTATIONS_CACHE";
	
	/**
	 * 缓存服务接口，需要实现该类的方法，接入即可以使用
	 */
	@Autowired
	private SwiftCacheService swiftCacheService;
	
	/**
	 * @annotation()可以使本切面成为某个注解的代理实现
	 * @param pjp
	 * @return
	 * @throws Throwable 
	 */
	@Around("@annotation(cache.annotation.SwiftCache)")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		String cacheKey = getCacheKey(pjp);
		Serializable serializable = swiftCacheService.get(CACHE_NAME, cacheKey);
		if(serializable != null) {
			// 命中缓存，返回缓存结果
			LOGGER.info("Cache Hit! key [{}]", cacheKey);
			return serializable;
		}
		LOGGER.info("Cache Miss! key [{}]", cacheKey);
		// 没有命中缓存，执行原方法
		Object result = pjp.proceed(pjp.getArgs());
		if(result == null) {
			LOGGER.error("Fail To Get Data From Source! key [{}]", cacheKey);
		} else {
			// 原方法执行返回结果不为空，加入缓存中
			SwiftCache swiftCache = getAnnotation(pjp, SwiftCache.class);
			swiftCacheService.put(CACHE_NAME, swiftCache.expire(), cacheKey, (Serializable) result);
		}
		return result;
	}

	/**
	 * 根据类名、方法名和参数值获取唯一的缓存键
	 * @param pjp
	 * @return
	 */
	private String getCacheKey(ProceedingJoinPoint pjp) {
		return String.format("%s.%s", pjp.getSignature().toString()
				.split("\\s")[1], StringUtils.join(pjp.getArgs(), ","));
	}
	
	private <T extends Annotation> T getAnnotation(ProceedingJoinPoint pjp, Class<T> clazz) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		return method.getAnnotation(clazz);
	}
}
