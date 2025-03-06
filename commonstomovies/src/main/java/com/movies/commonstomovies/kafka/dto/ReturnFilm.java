package com.movies.commonstomovies.kafka.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ReturnFilm {

    private String filmName;
    private String clientNumber;

    @Schema(description = "Return date of the film")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime returnDate;

    public ReturnFilm() {
    }

    public ReturnFilm(String filmName, String clientNumber) {
        this.filmName = filmName;
        this.clientNumber = clientNumber;
    }

    public ReturnFilm(String filmName, String clientNumber, LocalDateTime returnDate) {
        this.filmName = filmName;
        this.clientNumber = clientNumber;
        this.returnDate = returnDate;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "ReturnFilm{" +
                "filmName='" + filmName + '\'' +
                ", clientNumber='" + clientNumber + '\'' +
                '}';
    }
}
