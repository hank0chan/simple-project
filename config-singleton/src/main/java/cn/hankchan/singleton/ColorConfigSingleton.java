package cn.hankchan.singleton;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ColorConfigSingleton implements BaseSingleton {

	private List<String> colorConfig;
	/**
	 * 获取配置信息
	 */
	public List<String> getColorConfig() {
		return colorConfig;
	}
	private static class ColorConfigSingletonHolder {
		private static ColorConfigSingleton instance = new ColorConfigSingleton();
	}
	// 私有化构造器
	private ColorConfigSingleton() {
		colorConfig = new ArrayList<>();
		load();
	}
	/**
	 * 获取实例
	 */
	public static ColorConfigSingleton getInstance() {
		return ColorConfigSingletonHolder.instance;
	}
	
	@Override
	public void reload() {
		colorConfig.clear();
		load();
	}
	
	private void load() {
		SAXReader reader = new SAXReader();		
		try {
			Document doc = reader.read(this.getClass().getResourceAsStream("/singletonConfig/ColorConfig.xml"));
			Element rootElment = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> colorElements = rootElment.elements("Color");
			for(Element colorElement : colorElements) {
				String value = colorElement.getText();
				colorConfig.add(value);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
