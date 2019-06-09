package scences;

import client.ClientConsole;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.City;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static client.ChatClient.catalogDataS;
import static scences.CustomerHomeController.SearchTTV1;

public class GuestHomeController implements Initializable {

    @FXML
    private TableColumn<City, Integer> IDCOL2;

    @FXML
    private TableColumn<City, String> NameCOL2;

    @FXML
    private TableColumn<City, String> DescriptionCOL2;

    @FXML
    private TableColumn<City, Double> VersionCOL2;

    @FXML
    private TableColumn<City, Integer> NoMapsCOL2;

    @FXML
    private TableColumn<City, Integer> ToursCOL2;

    @FXML
    private TableColumn<City, Integer> LocationsCOL2;

    @FXML
    private TableColumn<City, Double> PriceCOL2;

    @FXML
    private TableColumn<City, Button> ActionCOL2;

    @FXML
    private Button SignInBTN;

    @FXML
    private TableView<City> SearchTTV2;

    @FXML
    private TextField searchBox2;

    @FXML
    private AnchorPane ChangePanesAP2;

    @FXML
    private Pane SerachCatalogPane2;

    @FXML
    private Button AdvancedSearchBTN2;

    @FXML
    private Button SearchCatalogBTN2;

    @FXML
    private Button RegisterBTN;

    @FXML
    private Button SearchBTN;


    @FXML
    void SearchCatalog2(ActionEvent event) {
        SearchTTV1.getItems().clear();
        boolean flag = false;
        String fillCityTable = "*SELECT * FROM Cities ";
        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTable);
    }

    @FXML
    void Register(ActionEvent event) throws IOException {
        String RegisterScene = "/scences/RegisterScene.fxml";
        ClientConsole.changeScene(RegisterScene);
    }

    @FXML
    void SignIn(ActionEvent event) throws IOException {
        String LogInSceneS = "/scences/LogInScene.fxml";
        ClientConsole.changeScene(LogInSceneS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SearchTTV1 = SearchTTV2;
        // cols for cities
        IDCOL2.setCellValueFactory(new PropertyValueFactory<>("cityID"));
        DescriptionCOL2.setCellValueFactory(new PropertyValueFactory<>("description"));
        VersionCOL2.setCellValueFactory(new PropertyValueFactory<>("mapClusterVersion"));
        NoMapsCOL2.setCellValueFactory(new PropertyValueFactory<>("numberMaps"));
        ToursCOL2.setCellValueFactory(new PropertyValueFactory<>("numberTours"));
        LocationsCOL2.setCellValueFactory(new PropertyValueFactory<>("numberLocations"));
        PriceCOL2.setCellValueFactory(new PropertyValueFactory<>("mapClusterPrice"));
        NameCOL2.setCellValueFactory(new PropertyValueFactory<>("cityName"));


    }

    @FXML
    private void searchRecord2(KeyEvent ke) {
        FilteredList<City> filterData = new FilteredList<>(catalogDataS, p -> true);
        searchBox2.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(city -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (city.getCityName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (city.getDescription().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });
            SortedList<City> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(SearchTTV1.comparatorProperty());
            SearchTTV1.setItems(sortedList);
        });
    }

}
