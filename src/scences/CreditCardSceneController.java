package scences;

import client.ChatClient;
import client.ClientConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class CreditCardSceneController implements Initializable {

    public static String cityNameCredit ;
    public static double mapClusterPriceCredit = 0;
    public static int cityIDCredit = -1;

    public static String cardtype ; // cardtype cardnumber expiredate cvv address fullName
    public static String cardnumber;
    public static String expiredate;
    public static int cvv = 0;
    public static String address;
    public static String fullName;

    @FXML
    private TextField cardnumberTF;

    @FXML
    private Button BackBTN;

    @FXML
    private TextField fullnameTF;

    @FXML
    private TextField cardtypeTF;

    @FXML
    private TextField expiredateTF;

    @FXML
    private Button SaveBuyBTN;

    @FXML
    private TextField adressTF;

    @FXML
    private Button BuyBTN;

    @FXML
    private TextField cvvTF;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String creditcarddetailsQuery = "NSELECT cardType, cardNumber, expireDate, cvv, Address, fullName FROM CreditCard WHERE cusID = " + ChatClient.customer.getCusID() ;
        boolean flag = ConnectionController.client.handleMessageFromClientUI(creditcarddetailsQuery);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!cardtype.equals("42")){
            cardtypeTF.setText(cardtype);
            cardnumberTF.setText(cardnumber);
            expiredateTF.setText(expiredate);
            cvvTF.setText( Integer.toString(cvv));
            adressTF.setText(address);
            fullnameTF.setText(fullName);
        }
    }

    @FXML
    void buyFunc(ActionEvent event) {
        AlertBoxCity.display(cityNameCredit, mapClusterPriceCredit, cityIDCredit);
    }

    @FXML
    void savebuyFunc(ActionEvent event) {
        String cardtype = cardtypeTF.getText();
        String cardnumber = cardnumberTF.getText();
        String expiredate = expiredateTF.getText();
        int cvv = Integer.parseInt(cvvTF.getText());
        String address = adressTF.getText();
        String fullname = fullnameTF.getText();

        String creditcardIdQuery = "MSELECT MAX(cardID) FROM CreditCard";

       boolean flag = ConnectionController.client.handleMessageFromClientUI(creditcardIdQuery);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String InsertCreditCard = "IINSERT INTO CreditCard(cardID, cusID, cardType, cardNumber, expireDate, cvv, Address , fullName ) VALUES ( "
                + ChatClient.maxcreditcardID +"," + ChatClient.customer.getCusID() + ",'" + cardtype + "','" + cardnumber + "','" + expiredate + "'," + cvv
                + ",'" + address + "','" + fullname + "')";
         flag = ConnectionController.client.handleMessageFromClientUI(InsertCreditCard);


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AlertBoxCity.display(cityNameCredit, mapClusterPriceCredit, cityIDCredit);

    }

    @FXML
    void backFunc(ActionEvent event) {
        String CustomerHomeScene = "/scences/CustomerHome.fxml";
        try {
            ClientConsole.changeScene(CustomerHomeScene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
