package models;
import db.ConstantQueries;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends User{

	private String userID;
	private int cusID;
	private int purchases;
	
	public Customer(String user, String pass, int cusID, int purchases) {
		super(user, pass);
		this.cusID = cusID;
		this.purchases = purchases;
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
}
