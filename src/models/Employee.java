package models;

public class Employee extends User {
    private String userID;
    private int employeeID;
    private int roleID;
    private String jobTitle;
    private String email;

    public Employee() {
    }
    public Employee(String user, String pass, String registerDate, int employeeID, int roleID, String jobTitle, String email  ) {
        super(user, pass, registerDate);
        this.employeeID = employeeID;
        this.roleID = roleID;
        this.jobTitle = jobTitle;
        this.email = email;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }





}
