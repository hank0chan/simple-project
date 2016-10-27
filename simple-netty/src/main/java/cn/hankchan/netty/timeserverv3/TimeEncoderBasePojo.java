package cn.hankchan.netty.timeserverv3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Oct 2016-16:44:04
 * <p>类说明:
 */
public class TimeEncoderBasePojo extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		 UnixTimeEntity m = (UnixTimeEntity) msg;
         ByteBuf encoded = ctx.alloc().buffer(4);
         encoded.writeInt((int) m.getValue());
         ctx.write(encoded, promise);
	}
}
