package models;

import scences.ConnectionController;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customer extends User {

	private String userID;
	private int cusID;
	private int purchases;
	private CustomerCard customerCard;
	private Map[] myMaps;


	public Customer () {
	}

	public Customer(String user, String pass, int cusID, int purchases, CustomerCard card, Map[] myMaps) {
		super(user, pass);
		this.cusID = cusID;
		this.purchases = purchases;
		this.customerCard = card;
		this.myMaps = myMaps;
	}

	public Customer(String user, String pass, int cusID, int purchases, CustomerCard card) {
		super(user, pass);
		this.cusID = cusID;
		this.purchases = purchases;
		this.customerCard = card;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}

	public Map[] getMyMaps() { return this.myMaps;}

	public void setMyMaps (Map[] myMaps){ this.myMaps = myMaps; }

	public Map[] getCustomerMaps(){
		String mapQuery = "aSelect * FROM F_Subscriptions WHERE cusID =  " + this.getCusID();
		boolean f1 = ConnectionController.client.handleMessageFromClientUI(mapQuery);

		mapQuery = "qSelect * FROM OT_Subscriptions WHERE cusID =  " + this.getCusID();
		f1 = ConnectionController.client.handleMessageFromClientUI(mapQuery);
	}
}
