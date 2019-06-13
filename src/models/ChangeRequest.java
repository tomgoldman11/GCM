package models;

public class ChangeRequest {

    public ChangeRequest(int requestID, String requestDescription, String employeeName, String requestDate) {

        this.requestID = requestID;
        this.requestDescription = requestDescription;
        this.employeeName = employeeName;
        this.requestDate = requestDate;
    }

    private int requestID;
    private String requestDescription;
    private String employeeName;
    private String requestDate;


    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }


}
