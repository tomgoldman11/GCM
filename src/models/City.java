package models;

public class City {
	private int cityID;
	private Map[] maps;
	private double mapClusterVersion;
	private double mapClusterPrice;
	private String cityName;
	
	public City(int cityID, Map[] maps, double mapClusterVersion, double mapClusterPrice, String cityName) {
		super();
		this.cityID = cityID;
		this.maps = maps;
		this.mapClusterVersion = mapClusterVersion;
		this.mapClusterPrice = mapClusterPrice;
		this.cityName = cityName;
	}
	
	public int getCityID() {
		return cityID;
	}
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	public Map[] getMaps() {
		return maps;
	}
	public void setMaps(Map[] maps) {
		this.maps = maps;
	}
	public double getMapClusterVersion() {
		return mapClusterVersion;
	}
	public void setMapClusterVersion(double mapClusterVersion) {
		this.mapClusterVersion = mapClusterVersion;
	}
	public double getMapClusterPrice() {
		return mapClusterPrice;
	}
	public void setMapClusterPrice(double mapClusterPrice) {
		this.mapClusterPrice = mapClusterPrice;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
	
	
	
	
	
	
	
}
