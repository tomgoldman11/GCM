package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tomgo
 *
 */
public class User {
	private String userID;
	private String password;
	private String registerDate;

	public User() {
	}

	public User(String user, String pass) {
		this.userID = user;
		this.password = pass;
	}

	public User(String user, String pass, String registerDate) {
		this.userID = user;
		this.password = pass;
		this.registerDate = registerDate;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

}
