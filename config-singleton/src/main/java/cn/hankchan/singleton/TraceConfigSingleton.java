package cn.hankchan.singleton;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TraceConfigSingleton implements BaseSingleton {

	private List<String> traceConfig;
	public List<String> getTraceConfig() {
		return traceConfig;
	}
	private static class TraceConfigSingletonHolder {
		private static TraceConfigSingleton instance = new TraceConfigSingleton();
	}
	public static TraceConfigSingleton getInstance() {
		return TraceConfigSingletonHolder.instance;
	}
	private TraceConfigSingleton() {
		traceConfig = new ArrayList<>();
		load();
	}
	@Override
	public void reload() {
		traceConfig.clear();
		load();
	}
	private void load() {
		SAXReader reader = new SAXReader();		
		try {
			Document doc = reader.read(this.getClass().getResourceAsStream("/singletonConfig/TraceConfig.xml"));
			Element rootElment = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> colorElements = rootElment.elements("Trace");
			for(Element colorElement : colorElements) {
				String value = colorElement.getText();
				traceConfig.add(value);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
