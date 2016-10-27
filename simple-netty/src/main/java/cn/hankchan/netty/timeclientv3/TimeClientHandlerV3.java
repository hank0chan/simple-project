package cn.hankchan.netty.timeclientv3;

import cn.hankchan.netty.timeserverv3.UnixTimeEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 时间服务器
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Oct 2016-15:39:45
 * <p>类说明:
 */
public class TimeClientHandlerV3 extends ChannelInboundHandlerAdapter {

	/**
	 * 从服务端接受一个32位的整数消息，把他翻译成人们能读懂的格式，并打印翻译好的时间，最后关闭连接
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		UnixTimeEntity m = (UnixTimeEntity) msg;
        System.out.println(m);
        ctx.close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 当出现异常就关闭连接
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}
	
}
