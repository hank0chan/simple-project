package cn.hankchan.test.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 测试代码
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 3 Oct 201613:14:22
 * <p>类说明:
 */
public class RpcTest {

	@Test
	public void test2() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					RpcExporter.exporter("localhost", 9290);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		RpcImporter<DataService> importer = new RpcImporter<>();
		DataService dataService = importer.importer(DataServiceImpl.class, new InetSocketAddress("localhost", 9290));
		System.out.println(dataService.getData(true));
	}
	
	/** 最简单的RPC框架测试 */
	@Test
	public void test() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					RpcExporter.exporter("localhost", 8089);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		RpcImporter<EchoService> importer = new RpcImporter<>();
		EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8089));
		System.out.println(echo.echo("Nothing.."));
	}
	
	/** Socket通信测试 */
	@Test
	public void test0() throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				InputStream in = null;
				DataInputStream dis = null;
				try {
					@SuppressWarnings("resource")
					ServerSocket server = new ServerSocket(9290);
					Socket client = server.accept();
					in = client.getInputStream();
					dis = new DataInputStream(in);
					System.out.println("i am the server: " + dis.readUTF());
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					try {
						dis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		Socket client = new Socket("localhost", 9290);
		OutputStream out = client.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		System.out.println("i am the client..");
		dos.writeUTF("client Msg-->Hello...");
		dos.close();
		out.close();
		client.close();
	}
}
