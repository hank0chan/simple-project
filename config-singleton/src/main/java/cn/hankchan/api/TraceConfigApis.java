package cn.hankchan.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hankchan.singleton.TraceConfigSingleton;

@Controller
public class TraceConfigApis {

	@RequestMapping("/trace.json")
	public @ResponseBody List<String> get() {
		return TraceConfigSingleton.getInstance().getTraceConfig();
	}
}
