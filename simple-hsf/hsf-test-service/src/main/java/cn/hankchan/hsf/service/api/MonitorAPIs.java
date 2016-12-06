package cn.hankchan.hsf.service.api;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonitorAPIs {

	@RequestMapping("/test/health.json")
	public @ResponseBody String checkHealth() {
		return "Health Monitor: " + new Date();
	}
}
