package models;

public class Tour {
	private int tourID;
	private String description;
	private Location[] locations;
	private Double[] visitDuration;
	
	public Tour(int tourID, String description, Location[] locations, Double[] visitDuration) {
		this.tourID = tourID;
		this.description = description;
		this.locations = locations;
		this.visitDuration = visitDuration;
	}
	public int getTourID() {
		return tourID;
	}
	public void setTourID(int tourID) {
		this.tourID = tourID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Location[] getLocations() {
		return locations;
	}
	public void setLocations(Location[] locations) {
		this.locations = locations;
	}
	public Double[] getVisitDuration() {
		return visitDuration;
	}
	public void setVisitDuration(Double[] visitDuration) {
		this.visitDuration = visitDuration;
	}
	
	
	
}
