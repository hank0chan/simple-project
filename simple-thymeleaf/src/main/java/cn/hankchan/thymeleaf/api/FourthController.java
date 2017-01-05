package cn.hankchan.thymeleaf.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.hankchan.thymeleaf.demo.entity.User;

@Controller
public class FourthController {

	/**
	 * 返回值类型不为void，通过指定ModelAttribute的value属性值
	 * 可以在模板页面通过${users}方式获取到该返回类型的数据
	 */
	@ModelAttribute("users")
	public List<User> beforeFourth() {
		List<User> users = new ArrayList<>();
		users.add(new User("1", "first-1", "last-1", "grava-1"));
		users.add(new User("2", "first-2", "last-2", "grava-2"));
		users.add(new User("3", "first-3", "last-3", "grava-3"));
		users.add(new User("4", "first-4", "last-4", "grava-4"));
		return users;
	}
	
	@RequestMapping("/fourth")
	public String fourthPage(Model model) {
		User user = new User("Alan", "Alan", "Alan", "nothing");
		model.addAttribute("user", user);
		model.addAttribute("firstName", "firstName from DB");
		model.addAttribute("lastName", "lastName from DB");
		return "fourth";
	}
	
	@RequestMapping("/user")
	public ModelAndView userPage() {
		ModelAndView modelAndView = new ModelAndView();
		
		// 将数据传递到模板页面，只需要放入该对象即可
		modelAndView.addObject("key", new Object());
		
		// 指定返回的模板视图名
		modelAndView.setViewName("user");
		return modelAndView;
	}
}
