package models;

public class Map {
	private int mapID;
	private String mapName;
	private String description;
	private double version;
	private Tour[] tours;
	private Location[] locations;
	private String mapPath;
	
	public Map(int mapID, String mapName, String description, double version, Tour[] tours, Location[] locations, String mapPath) {
		super();
		this.mapID = mapID;
		this.mapName = mapName;
		this.description = description;
		this.version = version;
		this.tours = tours;
		this.locations = locations;
		this.mapPath = mapPath;
	}
	
	public int getMapID() {
		return mapID;
	}
	public void setMapID(int mapID) {
		this.mapID = mapID;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public Tour[] getTours() {
		return tours;
	}
	public void setTours(Tour[] tours) {
		this.tours = tours;
	}
	public Location[] getLocations() {
		return locations;
	}
	public void setLocations(Location[] locations) {
		this.locations = locations;
	}

	public String getMapPath() {
		return mapPath;
	}

	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}
	
	
	
}
