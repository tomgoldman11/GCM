// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import common.*;
import java.sql.Date;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import models.*;
import ocsf.client.AbstractClient;
import scences.ConnectionController;
import scences.CustomerHomeController;
import scences.EmployeeHomeController;
import scences.LocationsToursDetailsController;

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
	public static Employee employee = new Employee();
	public static int maxCusID = 0;
	public static int maxOTSubID = 0;
	public static int maxFSubID = 0;
	public static int maxRequestID = 0;
	public static boolean CustomerFlag = true;
	public static ObservableList<City> catalogDataS = FXCollections.observableArrayList();
	public static ObservableList<Map> myMapsDataS = FXCollections.observableArrayList();
	public static ObservableList<Employee> employeesDataS = FXCollections.observableArrayList();
	public static ObservableList<ChangeRequest> requestsDataS = FXCollections.observableArrayList();
	public static ObservableList<Location> locationsDataS = FXCollections.observableArrayList();
	public static ObservableList<Tour> tourDataS = FXCollections.observableArrayList();
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
	public void updateEmployee(String user, String pass, String registerDate, int employeeID, int roleID, String jobTitle, String fullName, String email, String phone ) {
		this.employee.setUserID(user);
		this.employee.setPassword(pass);
		this.employee.setRegisterDate(registerDate);
		this.employee.setEmployeeID(employeeID);
		this.employee.setRoleID(roleID);
		this.employee.setJobTitle(jobTitle);
		this.employee.setFullName(fullName);
		this.employee.setEmail(email);
		this.employee.setPhone(phone);
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

		if (msg.toString().charAt(0) == 'E') {
			String[] splited = msg.toString().split("\\s+");
			if (splited[1].equals(ChatClient.usr.getUserID()))
				CustomerFlag = false;
			updateEmployee(ChatClient.usr.getUserID(), ChatClient.usr.getPassword(), ChatClient.usr.getRegisterDate(),
					Integer.parseInt(splited[2]), Integer.parseInt(splited[3]), splited[4], splited[5], splited[6], splited[7]);

		}

		if (msg.toString().charAt(0) == '!') {
			String[] splited = msg.toString().split("\\s+");
			updateCustomerCard(Integer.parseInt(splited[1]), splited[2] + " " + splited[3], Integer.parseInt(splited[4]), splited[5], splited[6]);
			handleMessageFromClientUI("@" + splited[1]);
		}

		if (msg.toString().charAt(0) == '(') {
			ChatClient.maxCusID = Integer.parseInt(msg.toString().substring(1)) + 1;
		}

		if (msg.toString().charAt(0) == 'v'){
			ChatClient.maxOTSubID = Integer.parseInt(msg.toString().substring(1)) + 1;
		}
		if (msg.toString().charAt(0) == 'c'){
			ChatClient.maxFSubID = Integer.parseInt(msg.toString().substring(1)) + 1;
		}
		if (msg.toString().charAt(0) == '3') {
			ChatClient.maxRequestID = Integer.parseInt(msg.toString().substring(1)) + 1;
		}


		if(msg instanceof ArrayList){
			if(ChatClient.CustomerFlag) {

				if (((ArrayList<String>) msg).get(0).equals("OTMaps")) {
					System.out.println("DEBUG: getting mapssssOT");


					((ArrayList<String>) msg).remove(0);
					ObservableList<Map> catalogDataMap = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 6) {

						catalogDataMap.add(new Map(
								Integer.parseInt(((ArrayList<String>) msg).get(i)), //4
								((ArrayList<String>) msg).get(i + 1), //HAIFAMPL;
								((ArrayList<String>) msg).get(i + 2) //hafia map
								, Double.parseDouble(((ArrayList<String>) msg).get(i + 3)), //1.1
								((ArrayList<String>) msg).get(i + 4), //file
								"-",
								Double.parseDouble(((ArrayList<String>) msg).get(i + 5)), //5
								new Button("show")));
					}

					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							myMapsDataS = catalogDataMap;
							CustomerHomeController.MyMapsTTV1.getItems().removeAll();
							CustomerHomeController.MyMapsTTV1.getItems().clear();
							CustomerHomeController.MyMapsTTV1.setItems(catalogDataMap);
							CustomerHomeController.MyMapsTTV1.refresh();


						}});
				}
				else if (((ArrayList<String>) msg).get(0).equals("FMaps")) {
					System.out.println("DEBUG: getting mapssssF");
					((ArrayList<String>) msg).remove(0);
					ObservableList<Map> catalogDataMap = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 7) {
						catalogDataMap.add(new Map(
								Integer.parseInt(((ArrayList<String>) msg).get(i)), //4
								((ArrayList<String>) msg).get(i + 1), //HAIFAMPL;
								((ArrayList<String>) msg).get(i + 2) //hafia map
								, Double.parseDouble(((ArrayList<String>) msg).get(i + 3)), //1.1
								((ArrayList<String>) msg).get(i + 4), //file
								((ArrayList<String>) msg).get(i + 5), //file,
								Double.parseDouble(((ArrayList<String>) msg).get(i + 6)), //5
								new Button("show")));
					}
					myMapsDataS.addAll(catalogDataMap);
					CustomerHomeController.MyMapsTTV1.setItems(myMapsDataS);
					CustomerHomeController.MyMapsTTV1.refresh();
				} else if (((ArrayList<String>) msg).get(0).equals("getcities")) {
					System.out.println("DEBUG: getting citiesss");
					((ArrayList<String>) msg).remove(0);
					ObservableList<City> catalogData = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 8) {
						catalogData.add(new City(Integer.parseInt(((ArrayList<String>) msg).get(i)), ((ArrayList<String>) msg).get(i + 1), Double.parseDouble(((ArrayList<String>) msg).get(i + 2))
								, Integer.parseInt(((ArrayList<String>) msg).get(i + 3)), Integer.parseInt(((ArrayList<String>) msg).get(i + 4)), Integer.parseInt(((ArrayList<String>) msg).get(i + 5))
								, Double.parseDouble(((ArrayList<String>) msg).get(i + 6)), ((ArrayList<String>) msg).get(i + 7), new Button("download")));
					}
					catalogDataS = catalogData;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							CustomerHomeController.SearchTTV1.getItems().removeAll();
							CustomerHomeController.SearchTTV1.getItems().clear();
							CustomerHomeController.SearchTTV1.setItems(catalogData);
							CustomerHomeController.SearchTTV1.refresh();
						}
					});

				}
				else if (((ArrayList<String>) msg).get(0).equals("getLocationsForMap")) {
					System.out.println("DEBUG: getting locationsForMap");
					((ArrayList<String>) msg).remove(0);
					ObservableList<Location> catalogData = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 5) {
						catalogData.add(new Location(Integer.parseInt(((ArrayList<String>) msg).get(i)), ((ArrayList<String>) msg).get(i + 1), ((ArrayList<String>) msg).get(i + 2)
								, (((ArrayList<String>) msg).get(i + 3)), Boolean.parseBoolean(((ArrayList<String>) msg).get(i + 4))));
					}
					locationsDataS = catalogData;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							LocationsToursDetailsController.LocationTV1.getItems().removeAll();
							LocationsToursDetailsController.LocationTV1.getItems().clear();
							LocationsToursDetailsController.LocationTV1.setItems(catalogData);
							LocationsToursDetailsController.LocationTV1.refresh();
						}
					});
				} // getToursForMap
				else if (((ArrayList<String>) msg).get(0).equals("getToursForMap")) {
					System.out.println("DEBUG: getting ToursForMap");
					((ArrayList<String>) msg).remove(0);
					ObservableList<Tour> catalogData = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 4) {
						catalogData.add(new Tour(Integer.parseInt(((ArrayList<String>) msg).get(i)), ((ArrayList<String>) msg).get(i + 1), ((ArrayList<String>) msg).get(i + 2),((ArrayList<String>) msg).get(i + 3) ));
					}
					tourDataS = catalogData;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							LocationsToursDetailsController.ToursTV1.getItems().removeAll();
							LocationsToursDetailsController.ToursTV1.getItems().clear();
							LocationsToursDetailsController.ToursTV1.setItems(catalogData);
							LocationsToursDetailsController.ToursTV1.refresh();
						}
					});
				}

			} // end if customer
			else {
				if (((ArrayList<String>) msg).get(0).equals("getcities")) {
					((ArrayList<String>) msg).remove(0);
					System.out.println("DEBUG:   HERE WE GO 3 3");
					ObservableList<City> catalogData = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 8) {
						catalogData.add(new City(Integer.parseInt(((ArrayList<String>) msg).get(i)), ((ArrayList<String>) msg).get(i + 1), Double.parseDouble(((ArrayList<String>) msg).get(i + 2))
								, Integer.parseInt(((ArrayList<String>) msg).get(i + 3)), Integer.parseInt(((ArrayList<String>) msg).get(i + 4)), Integer.parseInt(((ArrayList<String>) msg).get(i + 5))
								, Double.parseDouble(((ArrayList<String>) msg).get(i + 6)), ((ArrayList<String>) msg).get(i + 7), new Button("update")));
					}
					catalogDataS = catalogData;
					EmployeeHomeController.SearchTTV2.getItems().removeAll();
					EmployeeHomeController.SearchTTV2.getItems().clear();
					EmployeeHomeController.SearchTTV2.setItems(catalogData);
					EmployeeHomeController.SearchTTV2.refresh();

				}
				else if (((ArrayList<String>) msg).get(0).equals("Maps")) {
					System.out.println("DEBUG: getting allmaps");
					((ArrayList<String>) msg).remove(0);
					ObservableList<Map> catalogDataMap = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 4) {

						catalogDataMap.add(new Map(
								Integer.parseInt(((ArrayList<String>) msg).get(i)), //4
								((ArrayList<String>) msg).get(i + 1), //HAIFAMPL;
								((ArrayList<String>) msg).get(i + 2) //hafia map
								, Double.parseDouble(((ArrayList<String>) msg).get(i + 3)), new Button("update")));

					}
					myMapsDataS = catalogDataMap;
					EmployeeHomeController.MyMapsTTV1.setItems(catalogDataMap);
					EmployeeHomeController.MyMapsTTV1.refresh();
				}
				else if (((ArrayList<String>) msg).get(0).equals("getEmployees")) {
					((ArrayList<String>) msg).remove(0);
					ObservableList<Employee> catalogData = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 7) {
						catalogData.add(new Employee(((ArrayList<String>) msg).get(i), Integer.parseInt(((ArrayList<String>) msg).get(i + 1)), Integer.parseInt(((ArrayList<String>) msg).get(i + 2))
								, ((ArrayList<String>) msg).get(i + 3),((ArrayList<String>) msg).get(i + 4), ((ArrayList<String>) msg).get(i + 5)
								, ((ArrayList<String>) msg).get(i + 6)));
					}
					employeesDataS = catalogData;
					EmployeeHomeController.EmployeeTTV1.getItems().removeAll();
					EmployeeHomeController.EmployeeTTV1.getItems().clear();
					EmployeeHomeController.EmployeeTTV1.setItems(catalogData);
					EmployeeHomeController.EmployeeTTV1.refresh();

				}
				else if (((ArrayList<String>) msg).get(0).equals("Requests")) {
					((ArrayList<String>) msg).remove(0);
					System.out.println("DEBUG:   HERE WE GO Requests");
					ObservableList<ChangeRequest> catalogData = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 4) {
						catalogData.add(new ChangeRequest((Integer.parseInt(((ArrayList<String>) msg).get(i))), ((ArrayList<String>) msg).get(i + 1),((ArrayList<String>) msg).get(i + 2),
								((ArrayList<String>) msg).get(i + 3) ,new Button("see Details")));
					}
					requestsDataS = catalogData;
					EmployeeHomeController.RequestTTV1.getItems().removeAll();
					EmployeeHomeController.RequestTTV1.getItems().clear();
					EmployeeHomeController.RequestTTV1.setItems(catalogData);
					EmployeeHomeController.RequestTTV1.refresh();

				}

				else if (((ArrayList<String>) msg).get(0).equals("getLocationsForMap")) {
					System.out.println("DEBUG: getting locationsForMap");
					((ArrayList<String>) msg).remove(0);
					ObservableList<Location> catalogData = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 5) {
						catalogData.add(new Location(Integer.parseInt(((ArrayList<String>) msg).get(i)), ((ArrayList<String>) msg).get(i + 1), ((ArrayList<String>) msg).get(i + 2)
								, (((ArrayList<String>) msg).get(i + 3)), Boolean.parseBoolean(((ArrayList<String>) msg).get(i + 4))));
					}
					locationsDataS = catalogData;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							EmployeeHomeController.LocationTV1.getItems().removeAll();
							EmployeeHomeController.LocationTV1.getItems().clear();
							EmployeeHomeController.LocationTV1.setItems(catalogData);
							EmployeeHomeController.LocationTV1.refresh();
						}
					});
				} // getToursForMap
				else if (((ArrayList<String>) msg).get(0).equals("getToursForMap")) {
					System.out.println("DEBUG: getting ToursForMap");
					((ArrayList<String>) msg).remove(0);
					ObservableList<Tour> catalogData = FXCollections.observableArrayList();
					for (int i = 0; i < ((ArrayList) msg).size(); i += 4) {
						catalogData.add(new Tour(Integer.parseInt(((ArrayList<String>) msg).get(i)), ((ArrayList<String>) msg).get(i + 1), ((ArrayList<String>) msg).get(i + 2),((ArrayList<String>) msg).get(i + 3) ));
					}
					tourDataS = catalogData;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							EmployeeHomeController.ToursTV1.getItems().removeAll();
							EmployeeHomeController.ToursTV1.getItems().clear();
							EmployeeHomeController.ToursTV1.setItems(catalogData);
							EmployeeHomeController.ToursTV1.refresh();
						}
					});
				}
			}

		}// close big instance of IF
	} // close handle msg from server func

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message
	 *            The message from the UI.
	 */
	public boolean handleMessageFromClientUI(String message) {
		boolean LogInFlag = false;

		// Commands Detection

		if (message.charAt(0) == '-' || message.charAt(0) == '+' || message.charAt(0) == '(' || message.charAt(0) == ')' ||
				message.charAt(0) == '=' ||  message.charAt(0) == '*' || message.charAt(0) == 'm' || message.charAt(0) == 'n' ||
				message.charAt(0) == 'b' || message.charAt(0) == 'v' || message.charAt(0) == 'c' || message.charAt(0) == 'x' || message.charAt(0) == 'a'
				|| message.charAt(0) == 'q' || message.charAt(0) == ']' || message.charAt(0) == 'A' || message.charAt(0) == '5'  || message.charAt(0) == '6'
				|| message.charAt(0) == 'r' || message.charAt(0) == 'i' || message.charAt(0) == 'g' || message.charAt(0) == '>'|| message.charAt(0) == '3'
				|| message.charAt(0) == 'L' || message.charAt(0) == 'T')  {
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
			String messageEmployee,messageCustomer;
			message = message.replace("$", "");
			if (message.equals(this.usr.getPassword())) {
				LogInFlag = true;
				messageEmployee = "ESelect * From Employees WHERE userID =" + this.usr.getUserID();
				try {
					sendToServer(messageEmployee);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (CustomerFlag) {
					messageCustomer = "%Select * From Customers  WHERE userID =" + this.usr.getUserID();
					try {
						sendToServer(messageCustomer);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	public static void EraseDetails(){
		ChatClient.CustomerFlag = true;
		ChatClient.usr.setUserID("x");
		ChatClient.usr.setPassword("x");
		ChatClient.usr.setRegisterDate("x");
		ChatClient.customer.setUserID("x");
		ChatClient.customer.setPassword("x");
		ChatClient.customer.setPurchases(0);
		ChatClient.customer.setCusID(-1);
		ChatClient.customercard.setEmail("x");
		ChatClient.customercard.setCustomerName("x");
		ChatClient.customercard.setAge(-1);
		ChatClient.customercard.setPhone("0");
		ChatClient.customercard.setCusID(-1);
		ChatClient.employee.setEmail("x");
		ChatClient.employee.setEmployeeID(-1);
		ChatClient.employee.setFullName("x");
		ChatClient.employee.setJobTitle("x");
		ChatClient.employee.setPhone("0");
		ChatClient.employee.setRoleID(-1);
		ChatClient.employee.setUserID("0");
		ChatClient.employee.setPassword("x");
		ChatClient.employee.setRegisterDate("x");
	}
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
		exception.printStackTrace();
		System.out.println("The connection to the Server (" + getHost() + ", " + getPort() + ") has been disconnected");
	}
}

// End of ChatClient class
