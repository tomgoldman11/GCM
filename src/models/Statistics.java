package models;

public class Statistics {

    private int cityID;
    private String cityName;
    private int numOfPurchases;

    public Statistics (int cityID,String cityName,int numOfPurchases) {
        this.cityID = cityID;
        this.cityName = cityName;
        this.numOfPurchases = numOfPurchases;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getNumOfPurchases() {
        return numOfPurchases;
    }

    public void setNumOfPurchases(int numOfPurchases) {
        this.numOfPurchases = numOfPurchases;
    }
}


