package com.movies.commonstomovies.kafka.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class RequestRent {

    private String nameFilm;

    private String clientNumber;

    @Schema(description = "Rent date of the film")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime rentDate;

    public RequestRent(String nameFilm, String clientNumber) {
        this.nameFilm = nameFilm;
        this.clientNumber = clientNumber;
    }

    public RequestRent() {
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    @Override
    public String toString() {
        return "RequestRent{" +
                "nameFilm='" + nameFilm + '\'' +
                ", clientNumber='" + clientNumber + '\'' +
                '}';
    }
}
