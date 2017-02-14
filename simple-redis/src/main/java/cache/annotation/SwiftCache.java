package cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义缓存注解
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 16:54:06 - 13 Feb 2017
 * @detail 标注了这个注解的方法返回值将会被缓存，每次调用该方法将会首先从缓存中查找。
 * 如果没有命中的缓存才会执行当前方法，执行成功将会将结果加入缓存中。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SwiftCache {

	/**
	 * 缓存过期时间，单位是：秒。默认为：3600秒（1小时）
	 */
	int expire() default 3600;
}
