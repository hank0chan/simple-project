package cn.hankchan.thymeleaf.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import cn.hankchan.thymeleaf.demo.entity.User;

@RequestMapping("/test")
@Controller
public class IndexController {

	/**
	 * 根据请求URL，做业务数据的转发，存入Model对象，返回到模板页面，由Thymeleaf模板引擎渲染 
	 */
	@RequestMapping("/second")
	public String second(Model model) {
		Map<String, User> map = new HashMap<>();
		User user1 = new User("chenjm", "Chen", "Jiaming", "nothing");
		User user2 = new User("zhangt", "Zhang", "ting", "nothing");
		map.put("chenjm", user1);
		map.put("zhangt", user2);
		model.addAttribute("users", map);
		return "second";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("list", Lists.newArrayList("chenjiaming", "23years", "Guangzhou", ("now" + new Date())));
		return "index";
	}
	
	@RequestMapping("/health.json")
	public @ResponseBody String test() {
		return "Health Check:" + new Date();
	}
	
	@RequestMapping("map")
	public @ResponseBody Map<String, String> testThymeleaf(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		map.put("key", "Thymeleaf..");
		return map;
	}
	
}
