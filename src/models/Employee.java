package models;

public class Employee extends User {
    private String userID;
    private int employeeID;
    private int roleID;
    private String jobTitle;
    private String fullName;
    private String email;
    private String phone;

    public Employee() {
    }
    public Employee(String user, String pass, String registerDate, int employeeID, int roleID, String jobTitle, String fullName, String email ,String phone ) {
        super(user, pass, registerDate);
        this.employeeID = employeeID;
        this.roleID = roleID;
        this.jobTitle = jobTitle;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public Employee(String user, int employeeID, int roleID, String jobTitle, String fullName, String email ,String phone ) {
        this.setUserID(user);
        this.employeeID = employeeID;
        this.roleID = roleID;
        this.jobTitle = jobTitle;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
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
        return jobTitle.replace("_"," ");
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getFullName() {return this.fullName.replace("_"," ");}
    public void setFullName(String fullName){this.fullName = fullName;}
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {return this.phone;}
    public void setPhone(String phone){this.phone = phone;}





}