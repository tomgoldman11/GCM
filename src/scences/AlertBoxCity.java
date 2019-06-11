package scences;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AlertBoxCity {

    public static int maxOTID = 0;
    public static int maxFID = 0;

    public static void display(String cityName, double price , int cityID ) {
        Date currentdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
       // LocalDate futureDate = LocalDate.now().plusMonths(6);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(cityName + "Maps Purchase");
        window.setHeight(550);
        window.setWidth(700);

        Label canbuy = new Label("purchase status");
        String maxOTSubID = "vSELECT MAX(OTsubID) FROM OT_Subscriptions" ;
        String maxFSubID = "cSELECT MAX(FsubID) FROM F_Subscriptions";

        Label price1 = new Label("price for one time purchase is: " + price );
        Button buyOTButton = new Button("one time purchase");

        buyOTButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    boolean f1 = ConnectionController.client.handleMessageFromClientUI(maxOTSubID);

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException ew) {
                        ew.printStackTrace();
                    }

                    maxOTID = ChatClient.maxOTSubID;

                    String OTDetails = "bINSERT INTO OT_Subscriptions (OTsubID, cusID, cityID, purchaseDate, purchasePrice) " +
                            "VALUES (" + maxOTID + "," + ChatClient.customer.getCusID() + "," + cityID + ",'" + formatter.format(currentdate) + "'," +  price + ")";

                    boolean f2 = ConnectionController.client.handleMessageFromClientUI(OTDetails);

                    canbuy.setText("One-Time Purchase Completed Successfully");
                    canbuy.setTextFill(Color.GREEN);
                }});

        Label price2 = new Label("price for Fixed time purchase is: " + (price*2));
        Button buyFButton = new Button ("fixed time purchase");

        buyFButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean f1 = ConnectionController.client.handleMessageFromClientUI(maxFSubID);

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ew) {
                    ew.printStackTrace();
                }

                maxFID = ChatClient.maxFSubID;
                LocalDate futureDate = LocalDate.now().plusMonths(6);
                double fixedPrice = price*2;
                String FDetails = "xINSERT INTO F_Subscriptions (FsubID, cusID, cityID, purchaseDate, expireDate, purchasePrice) " +
                        "VALUES (" + maxFID + "," + ChatClient.customer.getCusID() + "," + cityID + ",'" + formatter.format(currentdate) + "','" + futureDate + "'," + fixedPrice + ")";

                boolean f2 = ConnectionController.client.handleMessageFromClientUI(FDetails);

                canbuy.setText("Fixed Purchase Completed Successfully");
                canbuy.setTextFill(Color.GREEN);


            }
        });



        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(price1, buyOTButton, price2, buyFButton, canbuy, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
