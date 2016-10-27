package cn.hankchan.socket.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {

	@SuppressWarnings("resource")
	@org.junit.Test
	public void server() {
		Socket client = null;
		InputStream in = null;
		DataInputStream dis = null;
		try {
			ServerSocket server = new ServerSocket(9290);
			client = server.accept();
			System.out.println("the client:" + client.getPort() + " is connected..");
			in = client.getInputStream();
			dis = new DataInputStream(in);
			System.out.println(dis.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		}
	}
	
	@org.junit.Test
	public void client() {
		Socket client = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		try {
			client = new Socket("localhost", 9290);
			out = client.getOutputStream();
			dos = new DataOutputStream(out);
			dos.writeUTF("Hello , i am the client...");
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			if(client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
