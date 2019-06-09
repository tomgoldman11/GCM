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
import models.CustomerCard;
import models.Map;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static client.ChatClient.*;

public class CustomerHomeController implements Initializable {


    private List<Node> AnchorPaneChildrens = new ArrayList<>();


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

    // mymaps cols

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
    private TableView<City> SearchTTV;

    @FXML
    public static TableView<City> SearchTTV1;

    @FXML
    public static TableView<Map> MyMapsTTV1;

    @FXML
    private Text MapsOwnedT;

    @FXML
    private Text PasswordT;

    @FXML
    private Button PersonalInfoBTN;

    @FXML
    private Text UserNameT;

    @FXML
    private Text AgeT;

    @FXML
    private AnchorPane ChangePanesAP;

    @FXML
    private Pane SerachCatalogPane;

    @FXML
    private TextField searchBox;

    @FXML
    private Button MyMapsBTN;

    @FXML
    private Button AdvancedSearchBTN;

    @FXML
    private Pane MyMapsPane;

    @FXML
    private Text EmailT;

    @FXML
    private Button SearchCatalogBTN;

    @FXML
    private Text PhoneT;

    @FXML
    private Button LogOutBTN;

    @FXML
    private Text IDT;

    @FXML
    private Button SearchBTN;

    @FXML
    private TableView<Map> MyMapsTTV;

    @FXML
    private Text FullNameT;

    @FXML
    private Button ChangeDetailsBTN;

    @FXML
    private TextField FullNameEditTF;

    @FXML
    private TextField AgeEditTF;

    @FXML
    private TextField PhoneEditTF;

    @FXML
    private TextField EmailEditTF;

    @FXML
    private TextField UserNameEditTF;

    @FXML
    private TextField PasswordEditTF;

    @FXML
    private Button ConfirmChangeBTN;

    @FXML
    private Button BackChangeBTN;

    @FXML
    private Label ChangeSL;


    @FXML
    void LogOut(ActionEvent event) {
        String LogInScene = "/scences/LogInScene.fxml";
        try {
            ClientConsole.changeScene(LogInScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PersonalInfo(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(2));
        IDT.setText(Integer.toString(customer.getCusID()));
        FullNameT.setText(customercard.getCustomerName());
        AgeT.setText(Integer.toString(customercard.getAge()));
        PhoneT.setText(customercard.getPhone());
        EmailT.setText(customercard.getEmail());
        UserNameT.setText(customer.getUserID());
        PasswordT.setText(customer.getPassword());
    }

    @FXML
    void MyMaps(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(1));
        MapsOwnedT.setText(Integer.toString(customer.getPurchases()));
        MyMapsTTV1.getItems().removeAll();
        MyMapsTTV1.getItems().clear();

        boolean flag = false;
        String fillCityTable = "mSELECT * FROM Maps WHERE cityID in (" +
                "SELECT cityID FROM OT_Subscriptions WHERE cusID = " + customer.getCusID() + ")";
        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTable);



}

    @FXML
    void SearchCatalog(ActionEvent event) {
        boolean flag = false;
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(0));
        SearchTTV1.getItems().removeAll();
        SearchTTV1.getItems().clear();
        String fillCityTable = "*SELECT * FROM Cities ";
        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTable);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPaneChildrens.addAll(ChangePanesAP.getChildren());
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(0));
        SearchTTV1 = SearchTTV;
        MyMapsTTV1 = MyMapsTTV;
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
        // cols for maps
        IDCOLMap.setCellValueFactory(new PropertyValueFactory<>("mapID"));
        descriptionnCOLMap.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameCOLMap.setCellValueFactory(new PropertyValueFactory<>("mapName"));
        VCOLMap.setCellValueFactory(new PropertyValueFactory<>("version"));
        ActionCOLMap.setCellValueFactory(new PropertyValueFactory<>("show"));
        //
        priceCOLMap.setCellValueFactory(new PropertyValueFactory<>("?"));
        tillCOLMap.setCellValueFactory(new PropertyValueFactory<>("?"));

        boolean flag = false;
        String fillCityTable = "*SELECT * FROM Cities ";
        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTable);
        // cols for maps
    }

    @FXML
    private void searchRecord(KeyEvent ke) {
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
            sortedList.comparatorProperty().bind(SearchTTV1.comparatorProperty());
            SearchTTV1.setItems(sortedList);
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
    @FXML
    void ConfirmChange(ActionEvent event) {
        boolean flag = false;
        String FullName = FullNameEditTF.getText();
        String Age = AgeEditTF.getText();
        String Phone = PhoneEditTF.getText();
        String Email = EmailEditTF.getText();
        String UserName = UserNameEditTF.getText();
        String Password = PasswordEditTF.getText();


        String customerCardDetailsChange = "=UPDATE CustomersCard SET customerName = '" + FullName +"',age = '" + Age + "',phone = '" + Phone + "', Email = '" + Email
                +"'"+"WHERE cusID = " + Integer.toString(ChatClient.customer.getCusID());
        flag = ConnectionController.client.handleMessageFromClientUI(customerCardDetailsChange);



        String userDetailsChange = "=UPDATE Users SET userID = '" + UserName +"',password = '" + Password + "'"
                +"WHERE userID = '" + ChatClient.usr.getUserID() + "'";
        flag = ConnectionController.client.handleMessageFromClientUI(userDetailsChange);

        ChangeSL.setText("Update Completed");
        ChangeSL.setTextFill(Color.BLUE);

        ChatClient.usr.setUserID(UserName);
        ChatClient.usr.setPassword(Password);
        ChatClient.customer.setUserID(UserName);
        ChatClient.customer.setPassword(Password);
        ChatClient.customercard.setAge(Integer.parseInt(Age));
        ChatClient.customercard.setCustomerName(FullName);
        ChatClient.customercard.setEmail(Email);
        ChatClient.customercard.setPhone(Phone);

    }
    @FXML
    void CancelChange(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(2));
    }

}
