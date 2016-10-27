package cn.hankchan.netty.timeclient;

import cn.hankchan.netty.timeserver.TimeDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 跟Discard协议和Echo协议不同，Time协议需要一个客户端
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Oct 2016-15:58:43
 * <p>类说明:
 */
public class TimeClientV2 {

	public static void main(String[] args) throws Exception {
		//String host = args[0];
		String host = "localhost";
		//int port = Integer.parseInt(args[1]);
		int port = 8080;
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// BootStrap 和 ServerBootstrap 类似,
			// 不过他是对非服务端的 channel 而言，比如客户端或者无连接传输模式的 channel
			Bootstrap b = new Bootstrap();
			// 如果你只指定了一个 EventLoopGroup，那他就会即作为一个 boss group ，
			// 也会作为一个 workder group，尽管客户端不需要使用到 boss worker 
			b.group(workerGroup);
			// 代替NioServerSocketChannel的是NioSocketChannel,这个类在客户端channel 被创建时使用
			b.channel(NioSocketChannel.class);
			// 不像在使用 ServerBootstrap 时需要用 childOption() 方法，因为客户端的 SocketChannel 没有父类
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					arg0.pipeline().addLast(new TimeDecoder(), new TimeClientHandlerV2());
				}
			});
			// 启动客户端，我们用 connect() 方法代替了 bind() 方法
			ChannelFuture f = b.connect(host, port).sync();
			// 等待连接关闭
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
