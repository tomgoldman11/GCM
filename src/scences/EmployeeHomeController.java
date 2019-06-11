package scences;


import client.ChatClient;
import client.ClientConsole;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.City;
import models.Employee;
import models.Map;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static client.ChatClient.*;


public class EmployeeHomeController implements Initializable {

    private List<Node> AnchorPaneChildrens = new ArrayList<>();

    @FXML
    private Button SearchBTN;

    @FXML
    private Button PersonalInfoBTN;

    @FXML
    private Button LogOutBTN;

    @FXML
    private AnchorPane ChangePanesAP;

    @FXML
    private Pane SerachCatalogPane;


    @FXML
    private TableView<City> SearchTTV;

    @FXML
    public static TableView<City> SearchTTV2;

    @FXML
    private TableColumn<City, Integer> IDCOL;

    @FXML
    private TableColumn<City, String> NameCOL;

    @FXML
    private TableColumn<City, String> DescriptionCOL;

    @FXML
    private TableColumn<City, Double> VersionCOL;

    @FXML
    private TableColumn<City, Integer> NoMapsCOL;

    @FXML
    private TableColumn<City, Integer> ToursCOL;

    @FXML
    private TableColumn<City, Integer> LocationsCOL;

    @FXML
    private TableColumn<City, Double> PriceCOL;

    @FXML
    private TableColumn<City, Button> ActionCOL;



    @FXML
    private TableColumn<Map, Integer> IDCOLMap;

    @FXML
    private TableColumn<Map, String> descriptionnCOLMap;

    @FXML
    private TableColumn<Map, String> nameCOLMap;

    @FXML
    private TableColumn<Map, Double> priceCOLMap;

    @FXML
    private TableColumn<Map, Double> VCOLMap;

    @FXML
    private TableColumn<Map, Double> tillCOLMap;

    @FXML
    private TableColumn<Map, Double> ActionCOLMap;



    @FXML
    private Button AdvancedSearchBTN;

    @FXML
    private TextField searchBox;

    @FXML
    private Text FullNameT;

    @FXML
    private Text JobTitleT;

    @FXML
    private Text EmailT;

    @FXML
    private Text PhoneT;

    @FXML
    private Text EmployeeIDT;

    @FXML
    private Text PasswordT;

    @FXML
    void LogOutEmployee(ActionEvent event) {
        ChatClient.EraseDetails();
        String LogInScene = "/scences/LogInScene.fxml";
        try {
            ClientConsole.changeScene(LogInScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PersonalInfoEmployee(ActionEvent event) {

        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(1));
        FullNameT.setText(employee.getFullName());
        JobTitleT.setText(employee.getJobTitle());
        EmailT.setText(employee.getEmail());
        PhoneT.setText(employee.getPhone());
        EmployeeIDT.setText(Integer.toString(employee.getEmployeeID()));
        PasswordT.setText(usr.getPassword());
    }

    @FXML
    void SearchCatalogEmployee(ActionEvent event) {
        boolean flag = false;
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(0));
        SearchTTV2.getItems().removeAll();
        SearchTTV2.getItems().clear();
        String fillCityTable = "*SELECT * FROM Cities ";
        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTable);
    }

    public void initialize(URL location, ResourceBundle resources) {
        AnchorPaneChildrens.addAll(ChangePanesAP.getChildren());
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(0));
        SearchTTV2 = SearchTTV;
        // cols for cities
        IDCOL.setCellValueFactory(new PropertyValueFactory<>("cityID"));
        DescriptionCOL.setCellValueFactory(new PropertyValueFactory<>("description"));
        VersionCOL.setCellValueFactory(new PropertyValueFactory<>("mapClusterVersion"));
        NoMapsCOL.setCellValueFactory(new PropertyValueFactory<>("numberMaps"));
        ToursCOL.setCellValueFactory(new PropertyValueFactory<>("numberTours"));
        LocationsCOL.setCellValueFactory(new PropertyValueFactory<>("numberLocations"));
        PriceCOL.setCellValueFactory(new PropertyValueFactory<>("mapClusterPrice"));
        NameCOL.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        ActionCOL.setCellValueFactory(new PropertyValueFactory<>("download"));

        boolean flag = false;
        String fillCityTable = "*SELECT * FROM Cities ";
        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTable);

        // cols for maps
        IDCOLMap.setCellValueFactory(new PropertyValueFactory<>("mapID"));
        descriptionnCOLMap.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameCOLMap.setCellValueFactory(new PropertyValueFactory<>("mapName"));
        VCOLMap.setCellValueFactory(new PropertyValueFactory<>("version"));
        ActionCOLMap.setCellValueFactory(new PropertyValueFactory<>("show"));
    }

    @FXML
    void searchRecord(KeyEvent event) {
        FilteredList<City> filterData = new FilteredList<>(catalogDataS, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
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
            sortedList.comparatorProperty().bind(SearchTTV2.comparatorProperty());
            SearchTTV2.setItems(sortedList);
        });
    }

    public static void addTextFilter(ObservableList<City> allData,
                                     TextField filterField, TableView<City> table) {

        final List<TableColumn<City, ?>> columns = table.getColumns();

        FilteredList<City> filteredData = new FilteredList<>(allData);
        filteredData.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            String text = filterField.getText();

            if (text == null || text.isEmpty()) {
                return null;
            }
            final String filterText = text.toLowerCase();

            return o -> {
                for (Object value : columns) {
                    if (value != null && value.toString().toLowerCase().contains(filterText)) {
                        return true;
                    }
                }
                return false;
            };
        }, filterField.textProperty()));

        SortedList<City> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    void Search(ActionEvent event) {
        // addTextFilter(catalogDataS, searchBox, SearchTTV1);
    }

    @FXML
    void ChangeAction(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(3));
    }

}

