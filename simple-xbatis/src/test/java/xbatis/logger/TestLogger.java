package xbatis.logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {

	private static final Logger xbatisLogger = LoggerFactory.getLogger("XBATIS");
	
	@Test
	public void test() {
		System.out.println("===>> Test Begining...");
		xbatisLogger.debug("This is the DEBUG test message..");
		xbatisLogger.info("This is the INFO test message..");
		xbatisLogger.error("This is the ERROR test message..");
		System.out.println("===>> Test End...");
	}
}
