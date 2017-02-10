package test.annotation;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

/**
 * 自定义注解"@PojoRefBean"的转换器
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 10:34:19 - 9 Feb 2017
 * @detail
 */
@Component
public class PojoRefBeanAnnotationCovertor {
	
	public void parseMethod(final Class<?> clazz) throws Exception {
		final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
		final Method[] methods = clazz.getDeclaredMethods();
		for(final Method method : methods) {
			final PojoRefBean pojoRefBean = method.getAnnotation(PojoRefBean.class);
			if(null != pojoRefBean) {
				method.invoke(obj, pojoRefBean.value());
			}
		}
	}
}
