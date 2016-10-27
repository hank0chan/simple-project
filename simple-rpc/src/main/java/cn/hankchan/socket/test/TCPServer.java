package cn.hankchan.socket.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(9290); // 1. 开放Server端接口
		Socket s = ss.accept(); // 3. 接受并且得到Client的连接
		System.out.println("A client Connected .. ");
		InputStream in = s.getInputStream();
		DataInputStream din = new DataInputStream(in);
		System.out.println(din.readUTF());
		din.close();
		s.close();
	}
}
