package cn.hankchan.netty.timeclient;

import java.util.Date;

import io.netty.buffer.ByteBuf;
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
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 从服务端接受一个32位的整数消息，把他翻译成人们能读懂的格式，并打印翻译好的时间，最后关闭连接
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf m = (ByteBuf) msg;
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 当出现异常就关闭连接
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}
	
}
