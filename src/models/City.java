package models;

import javafx.scene.control.Button;
import scences.AlertBoxCity;

import static client.ChatClient.catalogDataS;

public class City {
	private int cityID;
	private Map[] maps;
	private String description;
	private double mapClusterVersion;
	private int numberMaps;
	private int numberTours;
	private int numberLocations;
	private double mapClusterPrice;
	private String cityName;

	private Button download; // button for CustomerHome layout



	public City(int cityID, Map[] maps, String description, double mapClusterVersion, int numberMaps, int numberTours, int numberLocations, double mapClusterPrice, String cityName) {
		super();
		this.cityID = cityID;
		this.maps = maps;
		this.description = description;
		this.mapClusterVersion = mapClusterVersion;
		this.numberMaps = numberMaps;
		this.numberTours = numberTours;
		this.numberLocations = numberLocations;
		this.mapClusterPrice = mapClusterPrice;
		this.cityName = cityName;
	}

	public City(int cityID, String description, double mapClusterVersion, int numberMaps, int numberTours, int numberLocations, double mapClusterPrice, String cityName, Button download) {
		this.cityID = cityID;
		this.description = description;
		this.mapClusterVersion = mapClusterVersion;
		this.numberMaps = numberMaps;
		this.numberTours = numberTours;
		this.numberLocations = numberLocations;
		this.mapClusterPrice = mapClusterPrice;
		this.cityName = cityName;
		this.download = download;

		download.setOnAction(e -> {
			for(City city : catalogDataS ){
				if(city.getDownload() == download){
					AlertBoxCity.display(city.getCityName(), city.getMapClusterPrice(), city.getCityID());
				}
			}
		});
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberMaps() {
		return numberMaps;
	}

	public void setNumberMaps(int numberMaps) {
		this.numberMaps = numberMaps;
	}

	public int getNumberTours() {
		return numberTours;
	}

	public void setNumberTours(int numberTours) {
		this.numberTours = numberTours;
	}

	public int getNumberLocations() {
		return numberLocations;
	}

	public void setNumberLocations(int numberLocations) {
		this.numberLocations = numberLocations;
	}

	public Button getDownload() {
		return download;
	}

	public void setDownload(Button download) {
		this.download = download;
	}
}

	
	
	
	
	
	
	
