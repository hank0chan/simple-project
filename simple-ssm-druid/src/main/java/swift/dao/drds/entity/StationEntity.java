package swift.dao.drds.entity;

public class StationEntity {

	private String stationId;
	private String name;
	private float longitude;
	private float latitude;
	private int stationType;
	private float height;
	private String countyCode;
	private String prCode;
	private String areaCode;
	private String cityCode;
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public int getStationType() {
		return stationType;
	}
	public void setStationType(int stationType) {
		this.stationType = stationType;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getPrCode() {
		return prCode;
	}
	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	@Override
	public String toString() {
		return "StationEntity [stationId=" + stationId + ", name=" + name + ", longitude=" + longitude + ", latitude="
				+ latitude + ", stationType=" + stationType + ", height=" + height + ", countyCode=" + countyCode
				+ ", prCode=" + prCode + ", areaCode=" + areaCode + ", cityCode=" + cityCode + "]";
	}
	
}
