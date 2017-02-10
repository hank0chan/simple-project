package test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

public class PojoRefSpringBeanUtil extends ApplicationObjectSupport {

	private static ApplicationContext applicationContext = null;
	
	private static ApplicationContext getApplicationContextUtil() {
		return applicationContext;
	}
	
	public static Object getBean(String name) {
		return getApplicationContextUtil().getBean(name);
	}
	public static <T> T getBean(Class<T> requiredType) {
		return getApplicationContextUtil().getBean(requiredType);
	}
	
	@Override
	protected void initApplicationContext(ApplicationContext context) throws BeansException {
		super.initApplicationContext(context);
		if(PojoRefSpringBeanUtil.applicationContext == null) {
			PojoRefSpringBeanUtil.applicationContext = context;
		}
	}
}
