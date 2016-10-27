package cn.hankchan.dubbo.provider.main;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动服务
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 24 Oct 2016-14:18:55
 * @detail
 */
public class StartProvider {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"applicationContext.xml"}); // 部署的服务器地址信息http://ip/***/provider.xml
		context.start();
		System.out.println("This is the provider server.. button any key to quit");
		try {
			System.in.read(); // 按任意键退出
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
