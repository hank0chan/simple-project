package cn.hankchan.hsf.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hankchan.hsf.apis.HelloService;

@Controller
public class HelloAPIs {

	@Autowired
	HelloService helloService;
	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}
	
	@RequestMapping("/test/hello-{type}.json")
	public @ResponseBody String sayHello(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("type") int type) {
		String result = helloService.hello(type);
		return result;
	}
	
	@RequestMapping("/test/health.json")
	public @ResponseBody String checkHealth() {
		return "Health Monitor: " + new Date();
	}
}
