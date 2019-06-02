package scences;

import client.ClientConsole;
import client.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ConnectionController{

    @FXML
    private ImageView gcmIV;

    @FXML
    private TextField hostTF;

    @FXML
    private Text EnterHostT;

    @FXML
    private Button ConnetBTN;

    @FXML
    private TextField portTF;

    @FXML
    private Label ConnectionStatusL;

    @FXML
    private TextField LoginIDTF;

    @FXML
    private Text ConnetionT;

    @FXML
    private Text EnterLoginIDT;

    @FXML
    private Text EnterPortT;

    public static ChatClient client;

    public void Connect(ActionEvent event) throws IOException {
        try {
            client = new ChatClient(LoginIDTF.getText(), hostTF.getText(), Integer.parseInt(portTF.getText()));
        } catch (IOException e) {
            ConnectionStatusL.setText("Connection To Server Failed");
            ConnectionStatusL.setTextFill(Color.RED);
        }

        ConnectionStatusL.setText("Connection To Server Succeeded");
        ConnectionStatusL.setTextFill(Color.GREEN);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String LogInSceneS = "/scences/LogInScene.fxml";
        ClientConsole.changeScene(LogInSceneS);
    }
}
