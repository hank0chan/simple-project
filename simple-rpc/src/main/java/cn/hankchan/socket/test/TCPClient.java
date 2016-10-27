package cn.hankchan.socket.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws IOException, Exception {
		Socket s = new Socket("localhost", 9290); // 2. Client请求连接Server端
		OutputStream out = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("Hello, I am Client...");
		dos.flush();
		dos.close();
		s.close();
	}
}
