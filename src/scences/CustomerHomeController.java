package scences;

import client.ClientConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CustomerHomeController {

    @FXML
    private Button MyMapsBTN;

    @FXML
    private Button AdvancedSearchBTN;

    @FXML
    private Button PersonalInfoBTN;

    @FXML
    private Button SearchCatalogBTN;

    @FXML
    private Button LogOutBTN;

    @FXML
    private TextField SearchTF;

    @FXML
    private Button SearchBTN;

    @FXML
    private Pane SerachCatalogPane;

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

    }

    @FXML
    void MyMaps(ActionEvent event) {

    }

    @FXML
    void SearchCatalog(ActionEvent event) {

    }



}
