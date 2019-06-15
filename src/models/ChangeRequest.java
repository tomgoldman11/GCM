package models;

import javafx.scene.control.Button;
import scences.AlertBoxMap;
import scences.ConnectionController;

import java.util.concurrent.TimeUnit;

import static client.ChatClient.requestsDataS;

public class ChangeRequest {

    public ChangeRequest(int requestID, String requestDescription, String employeeName, String requestDate) {

        this.requestID = requestID;
        this.requestDescription = requestDescription;
        this.employeeName = employeeName;
        this.requestDate = requestDate;

    }

    public ChangeRequest(int requestID, String requestDescription, String employeeName, String requestDate , Button decline ,Button approve) {

        this.requestID = requestID;
        this.requestDescription = requestDescription;
        this.employeeName = employeeName;
        this.requestDate = requestDate;
        this.decline = decline;
        this.approve = approve;

        decline.setOnAction(e -> {
            for(ChangeRequest request : requestsDataS ){
                String removeRequest ;
                boolean flag;
                if(request.getDecline() == decline){
                    String DBtable = request.getRequestDescription().split("\\s+")[2];


                    if (DBtable.equals("City")){
                        removeRequest = "=DELETE FROM CitiesRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);

                        removeRequest = "=DELETE FROM ChangeRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);
                    }
                    else if (DBtable.equals("Map")){
                        removeRequest = "=DELETE FROM MapsRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);

                        removeRequest = "=DELETE FROM ChangeRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);
                    }
                    else if (DBtable.equals("Location")){
                        removeRequest = "=DELETE FROM LocationsRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);

                        removeRequest = "=DELETE FROM ChangeRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);
                    }
                    else if (DBtable.equals("Tour")){
                        removeRequest = "=DELETE FROM ToursRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);

                        removeRequest = "=DELETE FROM ChangeRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);
                    }

                }
            }
        });

        approve.setOnAction(e -> {
            for(ChangeRequest request : requestsDataS ){
                String removeRequest;
                boolean flag;
                if(request.getApprove() == approve){
                    String DBtable = request.getRequestDescription().split("\\s+")[2];


                    if (DBtable.equals("City") || DBtable.equals("city")){

                        String showRequest = "USELECT `cityID`, `description`, `mapsClusterVersion`, `numMaps`, `numTours`, `numLocations`," +
                                " `mapsClusterPrice`, `cityName` FROM CitiesRequest where requestID = " + request.getRequestID();
                        flag = ConnectionController.client.handleMessageFromClientUI(showRequest);

                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException err) {
                            err.printStackTrace();
                        }

                        removeRequest = "=DELETE FROM CitiesRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);

                        removeRequest = "=DELETE FROM ChangeRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);
                    }
                    else if (DBtable.equals("Map") || DBtable.equals("map")){

                        String showRequest = "PSELECT `mapID`, `cityID`, `mapName`, `description`, `version`, `mapPath` FROM `MapsRequest` WHERE `requestID` = " + request.getRequestID();
                        flag = ConnectionController.client.handleMessageFromClientUI(showRequest);

                        removeRequest = "=DELETE FROM MapsRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);

                        removeRequest = "=DELETE FROM ChangeRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);
                    }
                    else if (DBtable.equals("Location") || DBtable.equals("location")){
                        String showRequest = "SSELECT `locationID`, `locationName`, `classification`, `description`, `accessibility`, `cityID` FROM `LocationsRequest` WHERE `requestID` = "
                                + request.getRequestID();
                        flag = ConnectionController.client.handleMessageFromClientUI(showRequest);

                        removeRequest = "=DELETE FROM LocationsRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);

                        removeRequest = "=DELETE FROM ChangeRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);
                    }
                    else if (DBtable.equals("Tour") || DBtable.equals("tour")){
                        String showRequest = "VSELECT tourID, description, visitDuration, cityID, locationsID FROM `ToursRequest` WHERE `requestID` = "
                                + request.getRequestID();
                        flag = ConnectionController.client.handleMessageFromClientUI(showRequest);

                        removeRequest = "=DELETE FROM ToursRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);

                        removeRequest = "=DELETE FROM ChangeRequest WHERE requestID = " + request.getRequestID() ;
                        flag = ConnectionController.client.handleMessageFromClientUI(removeRequest);
                    }

                }
            }
        });

    }

    private int requestID;
    private String requestDescription;
    private String employeeName;
    private String requestDate;
    private Button decline;
    private Button approve;

    public Button getDecline() {
        return decline;
    }

    public void setDecline(Button decline) {
        this.decline = decline;
    }

    public Button getApprove() {
        return approve;
    }

    public void setApprove(Button approve) {
        this.approve = approve;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }


}