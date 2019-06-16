package models;
import javafx.scene.control.Button;

public class CustomerDetails {

    private String userID;
    private int customerID;
    private int numberofPurchases;
    private String customerName;
    private int customerAge;
    private String customerPhone;
    private String customerEmail;
    private Button show;


    public CustomerDetails(String userID, int customerID, int numberofPurchases, String customerName, int customerAge, String customerPhone, String customerEmail, Button show) {
        this.userID = userID;
        this.customerID = customerID;
        this.numberofPurchases = numberofPurchases;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.show = show;
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

    public void setCustomerName(String customerName) {
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
