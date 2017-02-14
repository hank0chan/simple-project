package cache.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializableUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(SerializableUtils.class);

	/**
	 * 序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj) {
		ObjectOutputStream objectOutputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(obj);
			byte[] bytes = byteArrayOutputStream.toByteArray();
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("序列化对象异常[" + e.getMessage() + "]", e);
		} finally {
			close(objectOutputStream, byteArrayOutputStream);
		}
		return null;
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unserialize(byte[] bytes) {
		if(bytes == null) {
			return null;
		}
		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			try {
				return (T) objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(objectInputStream, byteArrayInputStream);
		}
		return null;
	}

	/**
	 * 关闭资源（用于反序列化后）
	 * @param objectInputStream
	 * @param byteArrayInputStream
	 */
	private static void close(ObjectInputStream objectInputStream, ByteArrayInputStream byteArrayInputStream) {
		try {
			if(objectInputStream != null) {
				objectInputStream.close();
			}
			if(byteArrayInputStream != null) {
				byteArrayInputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("关闭IO资源异常[" + e.getMessage() + "]", e);
		}
	}
	
	/**
	 * 关闭资源（用于序列化后）
	 * @param objectOutputStream
	 * @param byteArrayOutputStream
	 */
	private static void close(ObjectOutputStream objectOutputStream, ByteArrayOutputStream byteArrayOutputStream) {
	
		try {
			if(byteArrayOutputStream != null) {
				byteArrayOutputStream.close();	
			}
			if(objectOutputStream != null) {
				objectOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("关闭IO资源异常[" + e.getMessage() + "]", e);
		}
	}
}
