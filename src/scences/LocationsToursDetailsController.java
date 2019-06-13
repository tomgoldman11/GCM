package scences;

import client.ClientConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.Location;
import models.Tour;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static models.Map.MapIDtoGetLocsTours;
import static models.Map.mapNameS;

public class LocationsToursDetailsController implements Initializable {

    private String mapName = "";
    private int mapID = -1;

    @FXML
    private Text MapNameT;

    @FXML
    private Button BackBTN;

    @FXML
    private TableColumn<Location, String> LocationDescriptionCOL;

    @FXML
    private TableColumn<Tour, String> DescriptionCOL;

    @FXML
    private TableColumn<Tour, String> TourDurationCOL;

    @FXML
    private TableView<Tour> ToursTV;

    @FXML
    public static TableView<Tour> ToursTV1;

    @FXML
    private TableColumn<Location, String> ClassificationCOL;

    @FXML
    private TableColumn<Location, String> LocationsIDCOL;

    @FXML
    private TableColumn<Location, Boolean > AccessibilityCOL;

    @FXML
    private TableColumn<Location, Integer> LocationIDCOL;

    @FXML
    private TableColumn<Tour, Integer> ToursIDCOL;

    @FXML
    private TableColumn<Location, String> NameCOL;

    @FXML
    private TableView<Location> LocationTV;

    @FXML
    public static TableView<Location> LocationTV1;



    @FXML
    void BackToCustomerHomeScreen(ActionEvent event) throws IOException {
        String CustomerHomeScene = "/scences/CustomerHome.fxml"; // main screen
        ClientConsole.changeScene(CustomerHomeScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapName = mapNameS;
        MapNameT.setText(mapName);
        mapID = MapIDtoGetLocsTours;
        LocationTV1 = LocationTV;
        ToursTV1 = ToursTV;
        // cols for Locations
        LocationIDCOL.setCellValueFactory(new PropertyValueFactory<>("locationID"));
        NameCOL.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        ClassificationCOL.setCellValueFactory(new PropertyValueFactory<>("locationClassification"));
        LocationDescriptionCOL.setCellValueFactory(new PropertyValueFactory<>("description"));
        AccessibilityCOL.setCellValueFactory(new PropertyValueFactory<>("accessibility"));
        // cols for Tours
        ToursIDCOL.setCellValueFactory(new PropertyValueFactory<>("tourID"));
        DescriptionCOL.setCellValueFactory(new PropertyValueFactory<>("description"));
        TourDurationCOL.setCellValueFactory(new PropertyValueFactory<>("visitDuration"));
        LocationsIDCOL.setCellValueFactory(new PropertyValueFactory<>("LocationsID"));

        boolean flag = false;
        String fillLocationsTable ="5SELECT * FROM Locations WHERE locationID in (SELECT locationID FROM Locations_Maps WHERE mapID = "+ mapID +")";
        flag = ConnectionController.client.handleMessageFromClientUI(fillLocationsTable);

        boolean flag1 = false;
        String fillToursTable ="6SELECT tourID,description,visitDuration,locationsID FROM Tours WHERE tourID in(SELECT tourID FROM Tours_Locations WHERE locationID in (SELECT locationID FROM Locations_Maps WHERE mapID = "+ mapID +"))";
        flag = ConnectionController.client.handleMessageFromClientUI(fillToursTable);
    }
}
