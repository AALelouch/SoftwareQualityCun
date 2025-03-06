package com.practice.clientapi.service;

import com.movies.commonstomovies.kafka.dto.RequestRent;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import com.practice.clientapi.entity.Client;
import com.practice.clientapi.model.FilmResponse;
import com.practice.clientapi.model.RegisterClient;

import java.util.List;

public interface ClientService {

    Client registerClient(RegisterClient registerClient);

    List<Client> getClients();

    Client getClientByClientNumber(String clientNumber);

    List<FilmResponse> getRentsMoviesByClientNumber(String clientNumber);

    void returnFilm(ReturnFilm returnFilm , String clientNumber);

    void rentFilm(RequestRent requestRent, String clientNumber);
}
