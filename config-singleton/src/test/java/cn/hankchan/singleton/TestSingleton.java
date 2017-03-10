package cn.hankchan.singleton;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

public class TestSingleton {

	@Test
	public void test() {
		String[] str = {};
		Arrays.asList(str);
	}
	
	// 测试输出配置信息
	@Test
	public void testSingletonConfig() {
		List<String> configs = ColorConfigSingleton.getInstance().getColorConfig();
		for (String config : configs) {
			System.out.println("Color: " + config);
		}
		List<String> configs2 = TraceConfigSingleton.getInstance().getTraceConfig();
		for (String config : configs2) {
			System.out.println("Trace: " + config);
		}
	}

	@Test
	public void testReloadOneSingleton() {
		// 传入单例名
		String singletonName = "colorConfigSingleton";
		
		BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		// 获取BaseSingleton的实现类
		TypeFilter filter = new AssignableTypeFilter(BaseSingleton.class);
		scanner.setIncludeAnnotationConfig(false);
		scanner.addIncludeFilter(filter);
		scanner.scan("cn.hankchan");
		// 根据SingletonName定位到具体类
		BeanDefinition beanDefinition = registry.getBeanDefinition(singletonName);
		String className = beanDefinition.getBeanClassName();
		try {
			Class<?> foundClass = Class.forName(className);
			if (BaseSingleton.class.isAssignableFrom(foundClass)) {
				// 开始执行重载
				Method method = foundClass.getMethod("getInstance", new Class[0]);
				BaseSingleton singleton = (BaseSingleton) method.invoke(null, new Object[0]);
				singleton.reload();
				System.out.println("重载成功！！！");
				// 重载结束
			}
		} catch (Exception e) {
			System.out.println("重载失败。。。");
		}

	}

	/**
	 * 获取所有的BaseSingleton接口的实现类
	 */
	@Test
	public void testSingletonImplementsClass() {
		BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		// 获取BaseSingleton的实现类
		TypeFilter filter = new AssignableTypeFilter(BaseSingleton.class);
		scanner.setIncludeAnnotationConfig(false);
		scanner.addIncludeFilter(filter);
		scanner.scan("cn.hankchan");
		String[] singletonImplementsClassesArr = registry.getBeanDefinitionNames();
		for (String singleton : singletonImplementsClassesArr) {
			System.out.println(singleton);
		}
	}

	/**
	 * 实现
	 */
	@Test
	public void testReloadAll() {
		BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		// 获取BaseSingleton的实现类
		TypeFilter filter = new AssignableTypeFilter(BaseSingleton.class);
		scanner.setIncludeAnnotationConfig(false);
		scanner.addIncludeFilter(filter);
		scanner.scan("cn.hankchan");
		Map<String, String> summary = new HashMap<String, String>();
		for (String beanName : registry.getBeanDefinitionNames()) {
			BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
			String className = beanDefinition.getBeanClassName();
			try {
				Class<?> foundClass = Class.forName(className);
				if (BaseSingleton.class.isAssignableFrom(foundClass)) {
					Method method = foundClass.getMethod("getInstance", new Class[0]);
					BaseSingleton singleton = (BaseSingleton) method.invoke(null, new Object[0]);
					singleton.reload();
					summary.put(className, "OK");
				}
			} catch (Exception x) {
				summary.put(className, x.getMessage());
			}
		}
	}
}
