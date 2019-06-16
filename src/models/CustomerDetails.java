package models;
import client.ClientConsole;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scences.ConnectionController;

import java.io.IOException;

import static client.ChatClient.CDforCustomerDetails;
import static scences.EmployeeHomeController.CusIDS;
import static scences.EmployeeHomeController.PurchasesS;
import static scences.EmployeeHomeController.CusNameS;

public class CustomerDetails {
    public String userID;
    public int customerID;
    public int numberofPurchases;
    public String customerName;
    public int customerAge;
    public String customerPhone;
    public String customerEmail;
    public Button show;


    public CustomerDetails(String userID, int customerID, int numberofPurchases, String customerName, int customerAge, String customerPhone, String customerEmail, Button show) {
        this.userID = userID;
        this.customerID = customerID;
        this.numberofPurchases = numberofPurchases;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.show = show;

        show.setOnAction(e -> {
            for(CustomerDetails customer : CDforCustomerDetails ){
                if (customer.getUserID().equals(userID)){
                    CusIDS = customer.getCustomerID();
                    CusNameS = customer.getCustomerName();
                    PurchasesS = customer.getNumberofPurchases();
                    String CustomerPurchases = "/scences/CustomerPurchases.fxml"; // main screen
                    try {
                        ClientConsole.changeScene(CustomerPurchases);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

//                VBox layout = new VBox(10);
//                layout.getChildren().addAll(MapIV,showLocsToursDetails);
//                layout.setAlignment(Pos.CENTER);
//
//                Scene scene = new Scene(layout);
//                window.setScene(scene);
//                // window.showAndWait();
//                window.show();

            }
        });
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getNumberofPurchases() {
        return numberofPurchases;
    }

    public void setNumberofPurchases(int numberofPurchases) {
        this.numberofPurchases = numberofPurchases;
    }

    public String getCustomerName() {
        return customerName;
    }

    public  void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Button getShow() {
        return show;
    }

    public void setShow(Button show) {
        this.show = show;
    }


}
