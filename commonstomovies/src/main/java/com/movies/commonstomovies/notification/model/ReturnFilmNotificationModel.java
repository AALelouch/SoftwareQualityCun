package com.movies.commonstomovies.notification.model;

public class ReturnFilmNotificationModel {

    private String name;

    private String clientNumber;

    private String rentDate;

    private String returnDate;

    private Integer daysRented;

    public ReturnFilmNotificationModel() {
    }

    public ReturnFilmNotificationModel(String name, String clientNumber, String rentDate, String returnDate, Integer daysRented) {
        this.name = name;
        this.clientNumber = clientNumber;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.daysRented = daysRented;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(Integer daysRented) {
        this.daysRented = daysRented;
    }

    @Override
    public String toString() {
        return "ReturnFilmNotificationModel{" +
                "name='" + name + '\'' +
                ", clientNumber='" + clientNumber + '\'' +
                ", rentDate=" + rentDate +
                ", returnDate=" + returnDate +
                ", daysRented=" + daysRented +
                '}';
    }
}
