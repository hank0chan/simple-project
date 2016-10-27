package cn.hankchan.dubbo.consumer.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hankchan.dubbo.service.HelloService;

/**
 * 消费服务
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 24 Oct 2016-14:27:09
 * @detail 首先在本地运行Zookeeper，然后运行StartProvider方法，接着运行StartConsumer方法。
 * StartConsumer方法中调用了Provider中的HelloService实现，得到输出结果：hello, I am Service Implements..
 * <p>也就是说，服务成功被注册到Zookeeper，然后被消费者调用，在Zookeeper控制台可以看到相关日志信息
 */
public class StartConsumer {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"classpath:applicationContext.xml"});  // http://ip/***/consumer.xml
		context.start();
		
		// 获取远程服务代理
		HelloService helloService = (HelloService) context.getBean("helloService");  
		String result = helloService.sayHello();
		// 显示调用结果
		System.out.println(result);
		
	}
}
