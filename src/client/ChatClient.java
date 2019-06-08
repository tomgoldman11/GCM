// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import common.*;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import models.City;
import models.Customer;
import models.User;
import models.CustomerCard;
import ocsf.client.AbstractClient;
import scences.CustomerHomeController;

import java.io.*;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;



/**
 * xv This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */

public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;

	/**
	 * The Login ID of the user.
	 */
	String loginID;

	// Constructors ****************************************************

	public static User usr = new User();
	public static CustomerCard customercard = new CustomerCard();
	public static Customer customer = new Customer();
	public static int maxCusID = 0;
	public static ObservableList<City> catalogDataS = FXCollections.observableArrayList();
	public static ObservableList<City> myMapsDataS = FXCollections.observableArrayList();
	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * @param clientUI
	 *            The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		openConnection();
		this.loginID = "";
		sendToServer("#login: ANONYMOUS");
	}

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param loginID
	 *            The user ID.
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 */

	public ChatClient(String loginID, String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor
		this.loginID = loginID;
		openConnection();

	}

	// Instance methods ************************************************



	public void updateUser(String userName, String password, String registerDate){
		this.usr.setUserID(userName);
		this.usr.setPassword(password);
		this.usr.setRegisterDate(registerDate);

	}

	public void updateCustomer(int CusID, int purchases , String password, String userID){
		this.customer.setCusID(CusID);
		this.customer.setPurchases(purchases);
		this.customer.setPassword(password);
		this.customer.setUserID(userID);
	}

	public void updateCustomerCard(int CusID, String CustomerName, int age, String Phone, String Email) {
		this.customercard.setCusID(CusID);
		this.customercard.setCustomerName(CustomerName);
		this.customercard.setAge(age);
		this.customercard.setPhone(Phone);
		this.customercard.setEmail(Email);
	}


	public void handleMessageFromServer(Object msg) {

		if (msg.toString().charAt(0) == '@') {
			String[] splited = msg.toString().split("\\s+");
			updateUser(splited[1], splited[2], splited[3]);
		}
		if (msg.toString().charAt(0) == '%') {
			String[] splited = msg.toString().split("\\s+");
			updateCustomer(Integer.parseInt(splited[2]), Integer.parseInt(splited[3]), this.usr.getPassword(), this.usr.getUserID() );
			String sql = "!SELECT * FROM CustomersCard WHERE cusID=" + this.customer.getCusID();
			try {
				sendToServer(sql);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (msg.toString().charAt(0) == '!') {
			String[] splited = msg.toString().split("\\s+");
			updateCustomerCard(Integer.parseInt(splited[1]), splited[2] + " " + splited[3], Integer.parseInt(splited[4]), splited[5], splited[6]);
			handleMessageFromClientUI("@" + splited[1]);
		}

		if (msg.toString().charAt(0) == '(') {
			ChatClient.maxCusID = Integer.parseInt(msg.toString().substring(1)) + 1;
		}

		if(msg instanceof ArrayList){
			System.out.println("DEBUG:   HERE WE GO 2 2");
			ObservableList<City> catalogData = FXCollections.observableArrayList();
			for(int i=0; i<((ArrayList) msg).size(); i+=8){
				catalogData.add(new City(Integer.parseInt(((ArrayList<String>)msg).get(i)), ((ArrayList<String>)msg).get(i+1),Double.parseDouble(((ArrayList<String>)msg).get(i+2))
				, Integer.parseInt(((ArrayList<String>)msg).get(i+3)),Integer.parseInt(((ArrayList<String>)msg).get(i+4)), Integer.parseInt(((ArrayList<String>)msg).get(i+5))
				, Double.parseDouble(((ArrayList<String>)msg).get(i+6)),  ((ArrayList<String>)msg).get(i+7) , new Button("download")));
			}
			catalogDataS = catalogData;
			CustomerHomeController.SearchTTV1.getItems().removeAll();
			CustomerHomeController.SearchTTV1.getItems().clear();
			CustomerHomeController.SearchTTV1.setItems(catalogData);
			CustomerHomeController.SearchTTV1.refresh();
		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message
	 *            The message from the UI.
	 */
	public boolean handleMessageFromClientUI(String message) {
        boolean LogInFlag = false;

		// Commands Detection

		if (message.charAt(0) == '-' || message.charAt(0) == '+' || message.charAt(0) == '(' || message.charAt(0) == ')' || message.charAt(0) == '=' ||  message.charAt(0) == '*' ) {
			try {
				System.out.println("msg:" +message);
				sendToServer(message);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return LogInFlag;
		}
		if (message.charAt(0) == '#') {
			runCommand(message);
		}

		if (message.charAt(0) == '!') {
			this.loginID = message.substring(1);
			String msg = "@Select * From Users  WHERE userID =" + this.loginID;
			try {
				sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (message.charAt(0) == '$') {
			message = message.replace("$", "");
			if (message.equals(this.usr.getPassword())) {
                LogInFlag = true;
                System.out.println(usr.getUserID());
				message = "%Select * From Customers  WHERE userID =" + this.usr.getUserID();
				try {
					sendToServer(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return LogInFlag;
		}

		if (message.equals("2")) {
			int newAmountOfPurchases = this.customer.getPurchases() + 1;
			this.customer.setPurchases(newAmountOfPurchases);
			try {
				sendToServer("$UPDATE Customers SET purchases = purchases + " + 1 + " WHERE userID="
						+ this.customer.getUserID());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return LogInFlag;
	}

	/**
	 * This method executes client commands. Benjamin Bergman, Oct 22, 2009
	 *
	 * @param message
	 *            string from the client console
	 */
	private void runCommand(String message) {
		// a bunch of ifs
		if (message.equalsIgnoreCase("#quit")) {
			quit();
		} else if (message.equalsIgnoreCase("#logoff")) {
			try {
				closeConnection();
			} catch (IOException e) {
			}
			clientUI.display("You have logged off.");
		} else if (message.toLowerCase().startsWith("#setport")) {
			// requires the command, followed by a space, then the port number
			try {
				int newPort = Integer.parseInt(message.substring(9));
				setPort(newPort);
				// error checking for syntax a possible addition
				clientUI.display("Port changed to " + getPort());
			} catch (Exception e) {
				System.out.println("Unexpected error while setting client port!");
			}
		} else if (message.toLowerCase().startsWith("#sethost")) {
			setHost(message.substring(9));
			clientUI.display("Host changed to " + getHost());
		} else if (message.toLowerCase().startsWith("#login")) {
			if (isConnected()) {
				clientUI.display("You must logout before you can login.");
				return;
			}
			// login
			// if no id, login anonymous
			loginID = message.substring(7);
			try {
				openConnection();
				sendToServer("#login " + loginID);
			} catch (Exception e) {
				clientUI.display("Connection could not be established.");
			}
		} else if (message.equalsIgnoreCase("#gethost")) {
			clientUI.display("Current host: " + getHost());
		} else if (message.equalsIgnoreCase("#getport")) {
			clientUI.display("Current port: " + Integer.toString(getPort()));
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	/**
	 * Reacts to a closed connection while waiting for messages from the server.
	 * Overrides method in <code>AbstractClient</code>. Added by Benjamin
	 * Bergman, Oct 22, 2009.
	 *
	 * @param exception
	 *            the exception raised.
	 */
	protected void connectionException(Exception exception) {
		System.out.println("The connection to the Server (" + getHost() + ", " + getPort() + ") has been disconnected");
		//clientUI.display("The connection to the Server (" + getHost() + ", " + getPort() + ") has been disconnected");
	}
}

// End of ChatClient class
