package cn.hankchan.hsf.service;

import java.util.Date;

import cn.hankchan.hsf.apis.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String hello(int type) {
		if(System.currentTimeMillis() % 2 == 0) {
			// 奇数
			if(type == 1) {
				return "type = 1, 时间奇数：" + new Date();
			} else {
				return "type != 1, 时间奇数：" + new Date();
			}
		} else {
			// 偶数
			if(type == 0) {
				return "type = 0, 时间偶数：" + new Date();
			} else {
				return "type != 0, 时间偶数：" +new Date();
			}
		}
	}
	
}
