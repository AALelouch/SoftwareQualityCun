package com.api.film.controller;


import com.api.film.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ClientController {

    @Operation(summary = "Get the client information using the id of film" +
            " with the endpoint of Client-Api")
    @ApiResponse(responseCode = "200", description = "Found the client",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class))})
    @ApiResponse(responseCode = "404", description = "Not Found the client",
            content = {@Content})
    ResponseEntity<Client> getClientByIdFilm(@Parameter(description = "Id for find the information" +
            "of client using the endpoint of client-api") Long idFilm);
}
