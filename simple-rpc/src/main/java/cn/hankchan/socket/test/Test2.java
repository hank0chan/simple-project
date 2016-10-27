package cn.hankchan.socket.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class Test2 {

	@org.junit.Test
	public void server() throws Exception {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(9290);
		Socket clientSocket = serverSocket.accept();
		InputStream in = clientSocket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		System.out.println(dis.readUTF());
		dis.close();
		in.close();
		clientSocket.close();
	}
	
	@Test
	public void client() throws Exception {
		Socket clientSocket = new Socket("localhost", 9290);
		OutputStream out = clientSocket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("I am the client..");
		dos.close();
		out.close();
		clientSocket.close();
	}
}
