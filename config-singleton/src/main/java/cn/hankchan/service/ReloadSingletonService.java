package cn.hankchan.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Service;

import cn.hankchan.singleton.BaseSingleton;

/**
 * 重载单例配置类
 * 
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 11:19:16 - 10 Mar 2017
 * @detail 只需要指定扫描的包名和修改Singleton接口就可以。
 */
@Service
public class ReloadSingletonService {

	private static final String PACKAGE_NAME = "cn.hankchan";
	
	public ReloadSingletonService() {
		initRegistry();
	}

	private BeanDefinitionRegistry registry;

	/**
	 * 重载一个指定的单例配置类
	 * @param singletonName 单例类名（首字母小写）
	 * @return 返回重载结果
	 */
	public String reload(String singletonName) {
		BeanDefinition beanDefinition = registry.getBeanDefinition(singletonName);
		// 获取全限定类名
		String className = beanDefinition.getBeanClassName();
		try {
			// 通过反射获得该类
			Class<?> foundClass = Class.forName(className);
			// 判断该类是否是BaseSingleton类的子类或接口
			if (BaseSingleton.class.isAssignableFrom(foundClass)) {
				Method method = foundClass.getMethod("getInstance", new Class[0]);
				BaseSingleton singleton = (BaseSingleton) method.invoke(null, new Object[0]);
				// 执行重载
				singleton.reload();
				return "Reload SUCCESS";
			}
			// 该类不是Singleton接口的子类
			return "Failure! The Sinleton: [" + singletonName + "] is Do Not Assignable From The Target Singleton Interface.";
		} catch (Exception x) {
			return x.getMessage();
		}
	}
	/**
	 * 重新载入所有的单例配置类
	 * @return 返回一个包括重载信息的Map，key为全类名，vaule为重载结果
	 */
	public Map<String, String> reloadAll() {
		Map<String, String> result = new HashMap<>();
		// 遍历该Singleton接口的子类的名称
		for (String singletonName : registry.getBeanDefinitionNames()) {
			BeanDefinition beanDefinition = registry.getBeanDefinition(singletonName);
			// 获取全限定类名
			String className = beanDefinition.getBeanClassName();
			try {
				// 通过反射获得该类
				Class<?> foundClass = Class.forName(className);
				// 判断该类是否是BaseSingleton类的子类或接口
				if (BaseSingleton.class.isAssignableFrom(foundClass)) {
					Method method = foundClass.getMethod("getInstance", new Class[0]);
					BaseSingleton singleton = (BaseSingleton) method.invoke(null, new Object[0]);
					// 执行重载
					singleton.reload();
					result.put(className, "Reload SUCCESS");
				}
			} catch (Exception x) {
				result.put(className, x.getMessage());
			}
		}
		return result;
	}
	
	/**
	 * 获取所有的Singleton名称
	 * @return 返回所有的Singleton类的类名，其中类名的首字母是小写。
	 * 如果没有类，返回空List。
	 */
	public List<String> getAllSingleton() {
		List<String> singletonNames = new ArrayList<>();
		for(String singletonName : registry.getBeanDefinitionNames()) {
			BeanDefinition beanDefinition = registry.getBeanDefinition(singletonName);
			String className = beanDefinition.getBeanClassName();
			try {
				Class<?> foundClass = Class.forName(className);
				if (BaseSingleton.class.isAssignableFrom(foundClass)) {
					singletonNames.add(singletonName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return singletonNames;
	}

	/**
	 * 初始化
	 */
	private void initRegistry() {
		BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		// 获取BaseSingleton的实现类
		TypeFilter filter = new AssignableTypeFilter(BaseSingleton.class);
		scanner.setIncludeAnnotationConfig(false);
		scanner.addIncludeFilter(filter);
		scanner.scan(PACKAGE_NAME);
		this.registry = registry;
	}
}
