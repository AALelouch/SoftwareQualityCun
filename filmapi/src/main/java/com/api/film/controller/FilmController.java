package com.api.film.controller;

import com.api.film.request.FilmRequest;
import com.api.film.response.FilmResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FilmController {

    @Operation(summary = "Create a film using a FilmRequest")
    @ApiResponse(responseCode = "201", description = "A film was created",
            content = {@Content(mediaType = "application/json")})
    void createFilm(FilmRequest filmRequest);

    @Operation(summary = "Get a film by id")
    @ApiResponse(responseCode = "200", description = "A film was found",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FilmResponse.class))})
    @ApiResponse(responseCode = "404", description = "Films not found",
            content = {@Content})
    @ApiResponse(responseCode = "400", description = "Invalid id",
            content = {@Content})
    ResponseEntity<FilmResponse> getFilmById(@PathVariable Long id);

    @Operation(summary = "Find the films rented")
    @ApiResponse(responseCode = "200", description = "The films was found",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FilmResponse.class))})
    @ApiResponse(responseCode = "400", description = "Invalid client number",
            content = {@Content})
    @ApiResponse(responseCode = "404", description = "Films rented not found",
            content = {@Content})
    ResponseEntity<List<FilmResponse>> getFilmsRentedByClientNumber(
            @PathVariable String clientNumber);

    @Operation(summary = "Find all the films")
    @ApiResponse(responseCode = "200", description = "The films was found",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FilmResponse.class))})
    ResponseEntity<List<FilmResponse>> getAllFilms();

    @Operation(summary = "Find all the films available")
    @ApiResponse(responseCode = "200", description = "The available films was found",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FilmResponse.class))})
    ResponseEntity<List<FilmResponse>> getAllFilmsAvailable();

}
