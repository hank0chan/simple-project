package cn.hankchan.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hankchan.service.ReloadSingletonService;

/**
 * 配置文件重载APIs
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 14:18:56 - 10 Mar 2017
 * @detail
 */
@Controller
public class ReloadSingletonApis {

	@Autowired
	ReloadSingletonService service;

	@RequestMapping("/reload-{singletonName}.json")
	public @ResponseBody String reload(@PathVariable("singletonName") String singletonName) {
		String result = service.reload(singletonName);
		return result;
	}
	
	@RequestMapping("/reload.json")
	public @ResponseBody Map<String, String> reloadAll() {
		Map<String, String> result = service.reloadAll();
		return result;
	}
	
	@RequestMapping("/all.json")
	public @ResponseBody List<String> getAllSingleton() {
		return service.getAllSingleton();
	}
}
