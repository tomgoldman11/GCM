package client;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.net.URL;

import javafx.scene.Parent;
import scences.*;
import client.*;
import common.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.*;

import static javafx.application.Application.launch;

/**
 * This class constructs the UI for a chat client. It implements the chat
 * interface in order to activate the display() method. Warning: Some of the
 * code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */

public class ClientConsole extends Application {

	// Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	ChatClient client;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the client.ClientConsole UI.
	 *
	 * @param host
	 *            The host to connect to.
	 * @param port
	 *            The port to connect on.
	 * @param loginID
	 *            The user's ID.
	 */
//	public client.ClientConsole(String loginID, String host, int port) {
//		try {
//			client = new ChatClient(loginID, host, port, this);
//		} catch (IOException exception) {
//			System.out.println("Error: Can't setup connection!" + " Terminating client.");
//			System.exit(1);
//		}
//	}

	// Instance methods ************************************************

	/**
	 * This method waits for input from the console. Once it is received, it
	 * sends it to the client's message handler.
	 */
	public void accept() {
		try {
			BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
			String message;

			while (true) {
				message = fromConsole.readLine();
				client.handleMessageFromClientUI(message);
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message
	 *            The string to be displayed.
	 */
	public void display(String message) {
		System.out.println(message);
	}

	// Class methods ***************************************************


	public static void main(String[] args) {
			launch(args);


//		String host = "";
//		int port = 0; // The port number
//		String loginID = "";
//		try {
//			loginID = args[0];
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("usage: java client.ClientConsole loginID [host [port]]");
//			System.exit(1);
//		}
//		try {
//			host = args[1];
//		} catch (ArrayIndexOutOfBoundsException e) {
//			host = "localhost";
//		}
//		try {
//			port = Integer.parseInt(args[2]);
//		} catch (ArrayIndexOutOfBoundsException e) {
//			port = DEFAULT_PORT;
//		}
//		client.ClientConsole chat = new client.ClientConsole(loginID, host, port);

 // starting the app here >!@?
		
//		System.out.println("Please enter ! userID:");
//
//		chat.accept(); // Wait for console data
	}

	private static Stage primaryStage;

	@Override
    public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
        URL url=getClass().getResource("/scences/ConnectionScene.fxml");
        Parent root= FXMLLoader.load(url);
        Scene ConnectionScene = new Scene(root);
        ConnectionScene.getStylesheets().add(getClass().getResource("/scences/AppStyle.css").toExternalForm());
        primaryStage.setScene(ConnectionScene);
        primaryStage.setTitle("GCM - Connection Page");
        primaryStage.show();



    }
    public static void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(ClientConsole.class.getResource(fxml));
		if( fxml == "/scences/RegisterScene.fxml")
        {
			primaryStage.setHeight(542);
			primaryStage.setWidth(525);
            primaryStage.setTitle("GCM - Registration");
        }
        if( fxml == "/scences/LogInScene.fxml")
        {
            primaryStage.setHeight(542);
            primaryStage.setWidth(525);
            primaryStage.setTitle("GCM - LogIn");
        }
		if( fxml == "/scences/CustomerHome.fxml")
		{
		    primaryStage.setHeight(760);
            primaryStage.setWidth(1040);
			primaryStage.setTitle("GCM - CustomerHome");
		}
		if( fxml == "/scences/EmployeeHome.fxml")
		{
			primaryStage.setHeight(760);
			primaryStage.setWidth(1040);
			primaryStage.setTitle("GCM - EmployeeHome");
		}
		if( fxml == "/scences/GuestHome.fxml")
		{
			primaryStage.setHeight(760);
			primaryStage.setWidth(1040);
			primaryStage.setTitle("GCM - GuestHome");
		}
		if( fxml == "/scences/LocationsToursDetails.fxml")
		{
			primaryStage.setHeight(820);
			primaryStage.setWidth(1500);
			primaryStage.setTitle("GCM - LocationToursDetails");
		}
		if( fxml == "/scences/CreditCardScene.fxml")
		{
			primaryStage.setHeight(550);
			primaryStage.setWidth(600);
			primaryStage.setTitle("GCM - Verify Payment");
		}
		if( fxml == "/scences/CustomerPurchases.fxml")
		{
			primaryStage.setHeight(900);
			primaryStage.setWidth(1100);
		}
		primaryStage.getScene().setRoot(pane);
	}


} // end ClientConsole
