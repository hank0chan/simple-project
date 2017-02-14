package cache.annotation;

import java.io.Serializable;
import java.util.Arrays;

public class DataEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstAttr;
	private String secondAttr;
	private String thirdAttr;
	private String fourthAttr;
	private float[][] datas;
	
	public DataEntity() {}
	
	public String getFirstAttr() {
		return firstAttr;
	}
	public void setFirstAttr(String firstAttr) {
		this.firstAttr = firstAttr;
	}
	public String getSecondAttr() {
		return secondAttr;
	}
	public void setSecondAttr(String secondAttr) {
		this.secondAttr = secondAttr;
	}
	public String getThirdAttr() {
		return thirdAttr;
	}
	public void setThirdAttr(String thirdAttr) {
		this.thirdAttr = thirdAttr;
	}
	public String getFourthAttr() {
		return fourthAttr;
	}
	public void setFourthAttr(String fourthAttr) {
		this.fourthAttr = fourthAttr;
	}
	public float[][] getDatas() {
		return datas;
	}
	public void setDatas(float[][] datas) {
		this.datas = datas;
	}
	@Override
	public String toString() {
		return "DataEntity [firstAttr=" + firstAttr + ", secondAttr=" + secondAttr + ", thirdAttr=" + thirdAttr
				+ ", fourthAttr=" + fourthAttr + ", datas=" + Arrays.toString(datas) + "]";
	}
	public DataEntity(String firstAttr, String secondAttr, String thirdAttr, String fourthAttr, float[][] datas) {
		super();
		this.firstAttr = firstAttr;
		this.secondAttr = secondAttr;
		this.thirdAttr = thirdAttr;
		this.fourthAttr = fourthAttr;
		this.datas = datas;
	}
	
}
