package models;
import db.ConstantQueries;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author tomgo
 *
 */
public class User {
	private String userID ;
	private String password ;
	private Date registerDate;
	
	
	public User(String user, String pass) {
		this.userID = user;
		this.password = pass;
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

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	   public User(ResultSet resultSet) throws SQLException {
	        this(resultSet.getString(ConstantQueries.USERS.COL_ID),
	               resultSet.getString(ConstantQueries.USERS.COL_PASSWORD));
	    }


}
