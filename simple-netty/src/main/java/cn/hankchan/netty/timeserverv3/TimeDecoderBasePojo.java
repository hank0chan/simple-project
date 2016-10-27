package cn.hankchan.netty.timeserverv3;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 时间编码处理器。
 * 需要插入到 ChannelPipeline 里，我们应该在 TimeClient 里修改 ChannelInitializer 的实现
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Oct 2016-16:21:16
 * <p>类说明: ByteToMessageDecoder 是 ChannelInboundHandler 的一个实现类，他可以在处理数据拆分的问题上变得很简单
 */
public class TimeDecoderBasePojo extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes() < 4) {
			return;
		}
		out.add(new UnixTimeEntity(in.readUnsignedInt()));
	}

}
