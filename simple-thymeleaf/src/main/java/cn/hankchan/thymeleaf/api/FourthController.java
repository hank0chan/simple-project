package cn.hankchan.thymeleaf.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hankchan.thymeleaf.demo.entity.User;

@Controller
public class FourthController {

	@RequestMapping("/fourth")
	public String fourthPage(Model model) {
		User user = new User("Alan", "Alan", "Alan", "nothing");
		model.addAttribute("user", user);
		model.addAttribute("firstName", "firstName from DB");
		model.addAttribute("lastName", "lastName from DB");
		return "fourth";
	}
}
