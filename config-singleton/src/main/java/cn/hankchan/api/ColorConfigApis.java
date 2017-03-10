package cn.hankchan.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hankchan.singleton.ColorConfigSingleton;

@Controller
public class ColorConfigApis {

	@RequestMapping("/color.json")
	public @ResponseBody List<String> get() {
		return ColorConfigSingleton.getInstance().getColorConfig();
	}
}
