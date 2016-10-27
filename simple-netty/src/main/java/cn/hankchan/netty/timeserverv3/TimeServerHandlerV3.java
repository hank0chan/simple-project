package cn.hankchan.netty.timeserverv3;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 时间服务器
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Oct 2016-15:39:45
 * <p>类说明:
 */
public class TimeServerHandlerV3 extends ChannelInboundHandlerAdapter {
	
	/** channelActive() 方法将会在连接被建立并且准备进行通信时被调用。
	 * 因此让我们在这个方法里完成一个代表当前时间的32位整数消息的构建工作。 */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		ChannelFuture f = ctx.writeAndFlush(new UnixTimeEntity());
        f.addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 当出现异常就关闭连接
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}
	
}
