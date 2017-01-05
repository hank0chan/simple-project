package cn.hankchan.thymeleaf.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import cn.hankchan.thymeleaf.demo.entity.User;

@RequestMapping("/test")
@Controller
public class IndexSecondThirdController {

	/**
	 * ModelAttribute标签修饰的方法会在该Controller中的
	 * 所有RequestMapping修饰的方法执行前执行。
	 * 可以用于在Model域中存放公共的数据
	 */
	@ModelAttribute
	public void beforeRequstAction(Model model) {
		model.addAttribute("myContextUrl", "http://localhost:8083");
	}
	
	/**
	 * 定义模板页面third.html中的Model对象 
	 */
	@RequestMapping("/third")
	public String third(Model model) {
		model.addAttribute("indexPage", "index.html");
		model.addAttribute("spitter", "..");
		model.addAttribute("register", "hello.html");
		model.addAttribute("id", "2");
		model.addAttribute("baidu", "http://www.baidu.com");
		model.addAttribute("userName", "Jiaming");
		model.addAttribute("lastName", "Chen");
		model.addAttribute("gravatar", "Nothing...");
		return "third";
	}
	
	/**
	 * 根据请求URL，做业务数据的转发，存入Model对象，返回到模板页面，由Thymeleaf模板引擎渲染 
	 */
	@RequestMapping("/second")
	public String second(Model model) {  // model用于存放数据模型
		Map<String, User> map = new HashMap<>();
		List<User> userList = new ArrayList<>();
		// 处理数据
		User user1 = new User();
		user1.setLogin("chenjm");
		user1.setFirstName("Chen");
		user1.setLastName("Jiaming");
		user1.setGravatar("nothing");
		User user2 = new User("zhangt", "Zhang", "ting", "nothing");
		// 封装数据
		map.put("chenjm", user1);
		map.put("zhangt", user2);
		userList.add(user1);
		userList.add(user2);
		// 放入Model。在页面中可以通过${...}获取相应的数据
		//如：${users.chenjm.firstName}的值为：Chen
		model.addAttribute("users", map);
		model.addAttribute("userList", userList);
		// 返回一个模板引擎渲染的页面结果，
		//这里为：/WEB-INF/templates/second.html
		return "second";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("list", Lists.newArrayList("chenjiaming", "23years", "Guangzhou", ("now" + new Date())));
		return "index";
	}
	
	@RequestMapping("/getUserByParam.json")
	public @ResponseBody User getUserByReqParam(@RequestParam("userName") String firstName, 
			@RequestParam("lastName") String lastName, @RequestParam("gravatar") String gravatar) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGravatar(gravatar);
		return user;
	}
	
	@RequestMapping("/get-{userId}.json")
	public @ResponseBody User getUser(@PathVariable("userId") String userId) {
		User user = null;
		if("1".equals(userId)) {
			user = new User("user-1", "Alan", "Alan", "id:1");
			return user; 
		} else {
			user = new User("user-9", "Zlatan", "Zlatan", "id:9");
			return user;
		}
	}
	
	@RequestMapping("/health.json")
	public @ResponseBody String test() {
		return "Health Check:" + new Date();
	}
	
	@RequestMapping("/map")
	public @ResponseBody Map<String, String> testThymeleaf(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		map.put("key", "Thymeleaf..");
		return map;
	}
	
}
