package cn.hankchan.netty.discardserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 第一个Netty服务端程序
 * 写个丢弃服务器
 * 处理服务端channel
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Oct 2016-14:47:31
 * <p>类说明: 为了实现 DISCARD 协议（这个协议将会丢掉任何收到的数据，而不响应），你只需忽略所有收到的数据。
 * 让我们从 handler （处理器）的实现开始，handler 是由 Netty 生成用来处理 I/O 事件的
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	/** 
	 * 每当从客户端收到新的数据时，这个方法会在收到消息时被调用，这个例子中，收到的消息的类型是 ByteBuf 
	 * 通常，channelRead() 方法的实现就像后面被屏蔽的代码
	 */
	/*@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 默默地丢弃收到的数据，不做处理
		((ByteBuf) msg).release();		
	}*/
	
	/** 被屏蔽的代码，测试连通性时启动 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		try {
			// 当你在CMD通过telnet localhost port 连接到DiscardServer之后，在CMD中输入内容
			while (in.isReadable()) {
				// 在服务器Console控制台将输出接收到内容
                System.out.print((char) in.readByte());
                System.out.flush();
			}
        } finally {  // 释放资源
            ReferenceCountUtil.release(msg);  // 或者，这里可以直接调用：in.release();
        }
	}
	
	/** 
	 * 为了实现 DISCARD 协议，处理器不得不忽略所有接受到的消息。ByteBuf 是一个引用计数对象，这个对象必须显示地调用 release() 方法来释放 
	 * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
	 * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
	 * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 当出现异常就关闭连接
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}
	
}
