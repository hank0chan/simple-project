package cn.hankchan.netty.timeserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 时间服务器 使用TIME协议，处理基于流的传输
 * 基于流的传输解决方案：主要通过加入一个时间编码处理器，修改了TimeClientV2的代码，其他的Server等部分并没有改变。
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Oct 2016-15:39:02
 * <p>类说明: 和之前的例子不同的是在不接受任何请求时他会发送一个含32位的整数的消息，
 * 并且一旦消息发送就会立即关闭连接。
 * 在这个例子中，构建和发送一个消息，然后在完成时关闭连接。
 */
public class TimeServerV2 {

	private int port;
	
	public TimeServerV2(int port) {
		this.port = port;
	}
	
	public static void main(String[] args) throws Exception {
		int port;
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new TimeServerV2(port).run();
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			 .channel(NioServerSocketChannel.class)  
			 .childHandler(new ChannelInitializer<SocketChannel>() {  
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeServerHandlerV2());
				}
			})
			  .option(ChannelOption.SO_BACKLOG, 128)  
			  .childOption(ChannelOption.SO_KEEPALIVE, true);
			
			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(port).sync();
			
			// 等待服务器 socket关闭
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
