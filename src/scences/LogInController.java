package scences;

import client.ChatClient;
import client.ClientConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class LogInController {

    @FXML
    private Button LogInBTN;

    @FXML
    private Button LogInGuestBTN;

    @FXML
    private PasswordField PasswordPF;

    @FXML
    private Text UserNameTF1;

    @FXML
    private Text UserNameT;

    @FXML
    private AnchorPane LogInAP;

    @FXML
    private TextField UserNameTF;

    @FXML
    private Label StatusL;

    public void Login(ActionEvent event) throws IOException {
        String userName = "!" + UserNameTF.getText();
        String password = "$" + PasswordPF.getText();
        boolean SignInFlag = false;
        ConnectionController.client.handleMessageFromClientUI(userName);
        SignInFlag = ConnectionController.client.handleMessageFromClientUI(password);
        if (SignInFlag) {
            StatusL.setText("Login Success");
            StatusL.setTextFill(Color.BLUE);
        }
        else {
            StatusL.setText("Login Failed");
            StatusL.setTextFill(Color.RED);
        }
    }

    public void Guest(ActionEvent event) throws IOException {
//        String RegisterScene = "/scences/RegisterScene.fxml"; // main screen
//        ClientConsole.changeScene(RegisterScene);


    }
    public void SignUp(ActionEvent event) throws IOException {
        String RegisterScene = "/scences/RegisterScene.fxml";
        ClientConsole.changeScene(RegisterScene);
    }
}
