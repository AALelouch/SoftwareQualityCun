package com.practice.clientapi.controller;

import com.movies.commonstomovies.kafka.dto.RequestRent;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import com.practice.clientapi.entity.Client;
import com.practice.clientapi.model.FilmResponse;
import com.practice.clientapi.model.RegisterClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IClientController {

    @Operation(summary = "Register new Client")
    @ApiResponse(responseCode = "201", description = "registered client",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) })
    @ApiResponse(responseCode = "400", description = "Bad Request")
    ResponseEntity<Client> registerClient(@RequestBody RegisterClient registerClient);


    @Operation(summary = "Get all register clients")
    @ApiResponse(responseCode = "200", description = "get clients list",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) })
    ResponseEntity<List<Client>> getClients();


    @Operation(summary = "Get client by client number")
    @ApiResponse(responseCode = "200", description = "get client",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) })
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Client not found")
    ResponseEntity<Client> getClientByClientNumber(@PathVariable String clientNumber);

    @Operation(summary = "Get client rented films by client number ")
    @ApiResponse(responseCode = "200", description = "get rented films",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FilmResponse.class)) })
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Client not found")
    List<FilmResponse> getRentsMoviesByClientNumber(@PathVariable String clientNumber);

    @Operation(summary = "Return a rented film")
    @ApiResponse(responseCode = "200", description = "return film")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Film not found")
    void returnFilm(@RequestBody ReturnFilm returnFilm, String clientNumber);

    @Operation(summary = "Return a rented film")
    @ApiResponse(responseCode = "200", description = "return film")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Film not found")
    void rentFilm(@RequestBody RequestRent requestRent, String clientNumber);
}
