package cn.hankchan.test.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * RPC客户端本地服务代理
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 3 Oct 201613:10:30
 * <p>类说明: 
 * <p>1. 将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用。
 * <p>2. 创建Socket客户端，根据指定地址连接远程服务提供者。
 * <p>3. 将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务提供者。
 * <p>4. 同步阻塞等待服务端返回应答，获取应答之后返回。
 * @param <S>
 */
public class RpcImporter<S> {

	@SuppressWarnings("unchecked")
	public S importer(final Class<?> serviceClass, final InetSocketAddress addr) {
		return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(), 
				new Class<?>[] {serviceClass.getInterfaces()[0]}, 
				new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Socket socket = null;
				ObjectOutputStream output = null;
				ObjectInputStream input = null;
				try {
					socket = new Socket();
					socket.connect(addr);
					output = new ObjectOutputStream(socket.getOutputStream());
					output.writeUTF(serviceClass.getName());
					output.writeUTF(method.getName());
					output.writeObject(method.getParameterTypes());
					output.writeObject(args);
					input = new ObjectInputStream(socket.getInputStream());
					return input.readObject();
				} finally {
					if(socket != null)
						socket.close();
					if(output != null)
						output.close();
					if(input != null)
						input.close();
				}
			}
		});
	}
}
