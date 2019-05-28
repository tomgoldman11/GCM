package models;

public class CustomerCard {

	private int cusID;
	private String customerName;
	private int age;
	private String phone;
	private String Email;

	public CustomerCard() {

	}

	public CustomerCard(int cusID, String customerName, int age, String phone, String Email) {
		this.customerName = customerName;
		this.age = age;
		this.phone = phone;
		this.Email = Email;
		this.cusID = cusID;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

}
