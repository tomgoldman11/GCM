package scences;

import client.ClientConsole;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import models.CustomerDetails;
import models.Map;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static client.ChatClient.CDforCustomerDetails;
import static client.ChatClient.customer;
import static scences.EmployeeHomeController.CusIDS;
import static scences.EmployeeHomeController.CusNameS;
import static scences.EmployeeHomeController.PurchasesS;

import models.CustomerDetails;


public class CustomerPurchasesHomeController  implements Initializable{

    @FXML
    private TableView<Map> MyCustomerMapsTV;

    @FXML
    public static TableView<Map> MyCustomerMapsTV1;

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
    private Text MapsOwnedT;

    @FXML
    private Text CustomerName;

    @FXML
    private Button BackBTN;




    public void initialize(URL location, ResourceBundle resources) {

        MyCustomerMapsTV1 = MyCustomerMapsTV;

        // cols for maps
        IDCOLMap.setCellValueFactory(new PropertyValueFactory<>("mapID"));
        descriptionnCOLMap.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameCOLMap.setCellValueFactory(new PropertyValueFactory<>("mapName"));
        VCOLMap.setCellValueFactory(new PropertyValueFactory<>("version"));
        priceCOLMap.setCellValueFactory(new PropertyValueFactory<>("price"));
        tillCOLMap.setCellValueFactory(new PropertyValueFactory<>("till"));

        MapsOwnedT.setText(Integer.toString(customer.getPurchases()));
        MyCustomerMapsTV1.getItems().removeAll();
        MyCustomerMapsTV1.getItems().clear();

        CustomerName.setText(CusNameS);
        MapsOwnedT.setText(Integer.toString(PurchasesS));
        boolean flag = false;
        String fillCityTableOT = "mSELECT m.* ,ot.purchasePrice FROM Maps m \n" +
                "LEFT JOIN OT_Subscriptions ot ON ot.cityID = m.cityID\n" +
                "WHERE m.cityID in (SELECT DISTINCT cityID FROM OT_Subscriptions WHERE cusID = " + CusIDS + ")";

        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTableOT);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String fillCityTableF = "nSELECT m.* ,f.purchasePrice, f.expireDate FROM Maps m \n" +
                "LEFT JOIN F_Subscriptions f ON f.cityID = m.cityID\n" +
                "WHERE m.cityID in (SELECT DISTINCT cityID FROM F_Subscriptions WHERE cusID = "  + CusIDS + ")";
        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTableF);

    }
    @FXML
    void BackToCustomerDetail(ActionEvent event)  {
        String CustomerPurchases = "/scences/EmployeeHome.fxml"; // main screen
        try {
            ClientConsole.changeScene(CustomerPurchases);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}



