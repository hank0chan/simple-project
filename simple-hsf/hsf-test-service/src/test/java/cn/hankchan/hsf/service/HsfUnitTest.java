package cn.hankchan.hsf.service;

import org.junit.Test;

import com.taobao.hsf.lightapi.ServiceFactory;

import cn.hankchan.hsf.apis.HelloService;

public class HsfUnitTest {

	private static final ServiceFactory FACTORY = 
			ServiceFactory.getInstanceWithPath("D:/taobao_tomcat_7.0/taobao-tomcat-7.0.59/deploy");  // pandora容器的SAR包路径
	
	@Test
	public void test() throws Exception {
		/** 发布服务，如果已经有服务提供者，则无需此代码 */
		FACTORY.provider("helloService")  // 参数是一个标识，初始化后，下次只需调用provider("helloProvider")即可拿出对应服务
			   .service("cn.hankchan.hsf.apis.HelloService")  // 接口全类名
			   .version("1.0.0")  // 版本号
			   .group("TEST_HSF")  // 组别
			   .impl(new HelloServiceImpl())  // 对应的服务实现
			   .publish();  // 发布服务，至少要调用service()和version()才可以发布服务
		
		/** 消费者获取服务 */
		FACTORY.consumer("helloService")  // 参数是一个标识，初始化后，下次只需调用consumer("helloConsumer")即可直接拿出对应服务
			   .service("cn.hankchan.hsf.apis.HelloService")  // 接口全类名
			   .version("1.0.0")  // 版本号
			   .group("TEST_HSF")  // 组别
			   .subscribe();
		
		FACTORY.consumer("helloService").sync();
		FACTORY.consumer("helloService").subscribe();
		
		/** 调用获得的服务实现 */
		HelloService helloService = (HelloService) FACTORY.consumer("helloService").subscribe();
		String result = helloService.hello(1);
		System.out.println(result);
	}
	
}
