# simple-dubbo
### 简单的Dubbo框架测试demo

#### 首先在本地运行Zookeeper，然后运行StartProvider方法，接着运行StartConsumer方法。
#### StartConsumer方法中调用了Provider中的HelloService实现，得到输出结果：hello, I am Service Implements..
#### 也就是说，服务成功被注册到Zookeeper，然后被消费者调用，在Zookeeper控制台可以看到相关日志信息

#### Provider的XML配置示例 
	<!-- 提供方应用信息 -->
	<dubbo:application name="simple-dubbo-provider" />
	<!-- Zookeeper -->
	<dubbo:registry protocol="zookeeper" address="localhost:2181" check="false"/>
	<!-- 用dubbo协议在20880端口暴露服务 -->
	<dubbo:protocol name="dubbo" port="20880" />
	<!-- 声明需要暴露的服务接口 -->
	<dubbo:service interface="cn.hankchan.dubbo.service.HelloService" ref="helloService" />
	<!-- 和本地的bean一样实现服务 -->
	<bean id="helloService" class="cn.hankchan.dubbo.service.impl.HelloServiceImpl" />

#### Consumer的XML配置示例
	<!-- 应用信息 -->
	<dubbo:application name="simple-dubbo-consumer" />	
	<!-- Zookeeper -->
	<dubbo:registry protocol="zookeeper" address="localhost:2181" check="false"/>	
	<!-- 消费服务引用 -->
	<dubbo:reference id="helloService" interface="cn.hankchan.dubbo.service.HelloService" />
	
待进一步更新。。
	
