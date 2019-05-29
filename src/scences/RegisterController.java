package scences;

import client.ClientConsole;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterController {



    Date currentdate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @FXML
    private Text SignInT;

    @FXML
    private Text PasswordT;

    @FXML
    private PasswordField PasswordPF;

    @FXML
    private Text UserNameT;

    @FXML
    private TextField UserNameTF;

    @FXML
    private TextField FullNameTF;

    @FXML
    private Text PasswordT1;

    @FXML
    private Text UserNameT1;

    @FXML
    private Text UserNameT3;

    @FXML
    private PasswordField PasswordPF1;

    @FXML
    private Text UserNameT2;

    @FXML
    private TextField AgeTF;

    @FXML
    private TextField EmailTF;

    @FXML
    private Button RegisterBTN;

    @FXML
    private TextField PhoneTF;

    @FXML
    private Button CancelBTN;

    @FXML
    void Register(ActionEvent event) {
        String fullname = FullNameTF.getText();
        String username = UserNameTF.getText();
        String password = PasswordPF.getText();
        String age = AgeTF.getText();
        String email = EmailTF.getText();
        String phone = PhoneTF.getText();






        String userDetails = "-INSERT INTO Users (userID, password, registerDate) " +
                "VALUES ("+ username +  ",'" + password + "','" +  formatter.format(currentdate) + "')" ;
        ChatClient.usr.setUserID(username);
        ChatClient.usr.setPassword(password);
        ChatClient.usr.setRegisterDate(formatter.format(currentdate));
        ConnectionController.client.handleMessageFromClientUI(userDetails);

        String cusIdQuery = "(SELECT MAX(cusID) FROM Customers ";
        ConnectionController.client.handleMessageFromClientUI(cusIdQuery);

        String customerDetails = "+INSERT INTO Customers (userID, cusID, purchases) " +
                "VALUES ("+ username +  "," + ChatClient.maxCusID +  0 + ")" ;


    }

    @FXML
    void Cancel(ActionEvent event) {
        String loginScene = "/scences/LogInScene.fxml";
        try {
            ClientConsole.changeScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
