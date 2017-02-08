package xbatis.data.sample.entity;

public class Sample {

	public String firstAttr;
	public String secondAttr;
	public String thirdAttr;
	public String fourthAttr;
	public int intNum;
	public float floatNum;
	
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
	public int getIntNum() {
		return intNum;
	}
	public void setIntNum(int intNum) {
		this.intNum = intNum;
	}
	public float getFloatNum() {
		return floatNum;
	}
	public void setFloatNum(float floatNum) {
		this.floatNum = floatNum;
	}
	@Override
	public String toString() {
		return "Sample [firstAttr=" + firstAttr + ", secondAttr=" + secondAttr + ", thirdAttr=" + thirdAttr
				+ ", fourthAttr=" + fourthAttr + ", intNum=" + intNum + ", floatNum=" + floatNum + "]";
	}
	
	
}
