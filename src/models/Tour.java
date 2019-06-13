package models;

public class Tour {
	private int tourID;
	private String description;
	private Location[] locations;
	private String visitDuration;
	private String LocationsID;
	
	public Tour(int tourID, String description, Location[] locations, String visitDuration, String LocationsID) {
		this.tourID = tourID;
		this.description = description;
		this.locations = locations;
		this.visitDuration = visitDuration;
		this.LocationsID = LocationsID;
	}
    public Tour(int tourID, String description, String visitDuration, String LocationnsID) {
		this.tourID = tourID;
		this.description = description;
		this.visitDuration = visitDuration;
		this.LocationsID = LocationnsID;
	}

	public String getLocationsID() {
		return LocationsID;
	}

	public void setLocationsID(String locationsID) {
		LocationsID = locationsID;
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
	public String getVisitDuration() {
		return visitDuration;
	}
	public void setVisitDuration(String visitDuration) {
		this.visitDuration = visitDuration;
	}
	
	
	
}
