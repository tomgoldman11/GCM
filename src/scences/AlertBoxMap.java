package scences;

import client.ChatClient;
import client.ClientConsole;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertBoxMap {

    public static void display(String mapNameTitle, String mapPath) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(mapNameTitle);
        window.setHeight(550);
        window.setWidth(700);
        ImageView MapIV = new ImageView(mapPath);
        MapIV.setFitHeight(400);
                MapIV.setFitWidth(400);
        String LocationsToursDetailsScene = "/scences/LocationsToursDetails.fxml"; // main screen
//        Button closeButton = new Button("close");
//        closeButton.setOnAction(e -> window.close());

        Button showLocsToursDetails = new Button("Locations_Tours Details");
        showLocsToursDetails.setOnAction(e ->
        {
            try {
                ClientConsole.changeScene(LocationsToursDetailsScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(MapIV,showLocsToursDetails);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
      // window.showAndWait();
        window.show();
    }
}
