package models;

import javafx.scene.control.Button;
import scences.AlertBoxUpdate;
import scences.ConnectionController;

import static client.ChatClient.locationsDataS;
import static client.ChatClient.myMapsDataS2;

public class Location {
	private int locationID;
	private Coordinate locationCoordiante; 
	private String locationName;
	private String locationClassification;
	private String description;
	private boolean accessibility;
	private Button show;
	
	public Location(int locationID, Coordinate locationCoordiante, String locationName, String locationClassification,
			String description, boolean accessibility) {
		this.locationID = locationID;
		this.locationCoordiante = locationCoordiante;
		this.locationName = locationName;
		this.locationClassification = locationClassification;
		this.description = description;
		this.accessibility = accessibility;
	}
	public Location(int locationID, String locationName, String locationClassification,
					String description, boolean accessibility) {
		this.locationID = locationID;
		this.locationName = locationName;
		this.locationClassification = locationClassification;
		this.description = description;
		this.accessibility = accessibility;
	}
	public Location(int locationID, String locationName, String locationClassification,
					String description, boolean accessibility, Button show) {
		this.locationID = locationID;
		this.locationName = locationName;
		this.locationClassification = locationClassification;
		this.description = description;
		this.accessibility = accessibility;
		this.show = show;

		show.setOnAction(e -> {
			for(Location loc : locationsDataS ){
				if(loc.getShow() == show){
					int locationid = loc.getLocationID();
					String name = loc.getLocationName();
					String classification = loc.getLocationClassification();
					String locationdesc = loc.getDescription();
					boolean access = loc.isAccessibility();

					String locationChangeUpdate = "=UPDATE Locations SET locationID = " + locationid +",description = '" + locationdesc + "',accessibility = " + access
							+ ",locationName = '" + name + "'" + ",classification = '" + classification + "'WHERE locationID = " + locationid ;
					boolean flag = ConnectionController.client.handleMessageFromClientUI(locationChangeUpdate);

					AlertBoxUpdate.display("Update Status", "Update Completed");
				}
			}
		});
	}

	public Button getShow() {
		return show;
	}

	public void setShow(Button show) {
		this.show = show;
	}

	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public Coordinate getLocationCoordiante() {
		return locationCoordiante;
	}
	public void setLocationCoordiante(Coordinate locationCoordiante) {
		this.locationCoordiante = locationCoordiante;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationClassification() {
		return locationClassification;
	}
	public void setLocationClassification(String locationClassification) {
		this.locationClassification = locationClassification;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAccessibility() {
		return accessibility;
	}
	public void setAccessibility(boolean accessibility) {
		this.accessibility = accessibility;
	}
	
}
