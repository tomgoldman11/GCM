package models;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import scences.AlertBoxMap;
import scences.AlertBoxUpdate;
import scences.ConnectionController;

import static client.ChatClient.*;


public class Map2 {
    private int mapID;
    private String mapName;
    private String description;
    private double version;
    private Tour[] tours;
    private Location[] locations;
    private String mapPath;
    private Button show;
    private String till = "-";
    private double price = 0;
    public static int MapIDtoGetLocsTours = 1;
    public static String mapNameS = "";


    public Map2(int mapID, String mapName, String description, double version, Tour[] tours, Location[] locations, String mapPath, Button show) {
        this.mapID = mapID;
        this.mapName = mapName;
        this.description = description;
        this.version = version;
        this.tours = tours;
        this.locations = locations;
        this.mapPath = mapPath;
        this.show = show;
    }

    public Map2(int mapID, String mapName, String description, double version, String mapPath, Button show) {
        this.mapID = mapID;
        this.mapName = mapName;
        this.description = description;
        this.version = version;
        this.tours = tours;
        this.locations = locations;
        this.mapPath = mapPath;
        this.show = show;

        show.setOnAction(e -> {
            for(Map map : myMapsDataS ){
                if(map.getShow() == show){
                    AlertBoxMap.display(map.getMapName(), map.getMapPath());
                }
            }
        });
    }

    public Map2(int mapID, String mapName, String description, double version, Button show) {
        this.mapID = mapID;
        this.mapName = mapName;
        this.description = description;
        this.version = version;
        this.mapPath = mapPath;
        this.show = show;

        show.setOnAction(e -> {
            for(Map2 map : myMapsDataS2 ){
                if(map.getShow() == show){
                            int mapid = map.getMapID();
                            String desc = map.getDescription();
                            double mapver = map.getVersion();
                            String mapname = map.getMapName();

                            String mapChangeUpdate = "=UPDATE Maps SET mapID = " + mapid +",description = '" + desc + "',version = " + mapver
                                    + ",mapName = '" + mapname + "'" + "WHERE mapID = " + mapid ;
                            boolean flag = ConnectionController.client.handleMessageFromClientUI(mapChangeUpdate);

                            AlertBoxUpdate.display("Update Status", "Update Completed");
                }
            }
        });
    }

    public Map2(int mapID, String mapName, String description, double version, String mapPath, String till , double price, Button show) {
        System.out.println("==== my price brings all the boys to thge uard --- " + price);
        this.mapID = mapID;
        this.mapName = mapName;
        this.description = description;
        this.version = version;
        this.tours = tours;
        this.locations = locations;
        this.mapPath = mapPath;
        this.till = till;
        this.price = price;
        this.show = show;

        show.setOnAction(e -> {
            for(Map map : myMapsDataS ){
                if(map.getShow() == show){
                    MapIDtoGetLocsTours = map.getMapID();
                    mapNameS = map.getMapName();
                    AlertBoxMap.display(map.getMapName(), map.getMapPath());
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
    public double getPrice(){return this.price;}
    public String getTill(){return this.till;}



}
