package scences;

import scences.*;
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
    
    public void LogIn(ActionEvent event) {
    	if(true) {// login successfull
    	StatusL.setText("Login Success");
    	StatusL.setTextFill(Color.BLUE);
    	}
    	else {
        StatusL.setText("Login Success");
    	StatusL.setTextFill(Color.RED);
    	}
    }

}
