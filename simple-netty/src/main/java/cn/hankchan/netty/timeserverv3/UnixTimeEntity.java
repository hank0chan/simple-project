package cn.hankchan.netty.timeserverv3;

/**
 * POJO实体类
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 27 Oct 2016-16:37:17
 * <p>类说明:
 */
public class UnixTimeEntity {

	private final long value;

	public UnixTimeEntity() {
		this(System.currentTimeMillis() / 1000L + 2208988800L);
	}
	public UnixTimeEntity(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "UnixTimeEntity [value=" + value + "]";
	}
}
