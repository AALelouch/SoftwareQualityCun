package com.api.film.controller.impl;

import com.api.film.controller.FilmController;
import com.api.film.request.FilmRequest;
import com.api.film.response.FilmResponse;
import com.api.film.service.film.CreateFilmCommand;
import com.api.film.service.film.GetFilmQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmControllerImpl implements FilmController {
    private final CreateFilmCommand createFilmCommandImpl;
    private final GetFilmQuery getFilmQueryImpl;

    public FilmControllerImpl(CreateFilmCommand createFilmCommandImpl, GetFilmQuery getFilmQueryImpl) {
        this.createFilmCommandImpl = createFilmCommandImpl;
        this.getFilmQueryImpl = getFilmQueryImpl;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFilm(@RequestBody FilmRequest filmRequest) {

        createFilmCommandImpl.createFilm(filmRequest);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FilmResponse> getFilmById(@PathVariable Long id) {

        return ResponseEntity.ok(getFilmQueryImpl.byId(id));
    }

    @Override
    @GetMapping("/rented/{clientNumber}")
    public ResponseEntity<List<FilmResponse>> getFilmsRentedByClientNumber(
            @PathVariable String clientNumber) {

        return ResponseEntity.ok(getFilmQueryImpl.byClientNumber(clientNumber));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<FilmResponse>> getAllFilms() {
        return ResponseEntity.ok(getFilmQueryImpl.getAll());
    }

    @Override
    @GetMapping("/available")
    public ResponseEntity<List<FilmResponse>> getAllFilmsAvailable() {

        return ResponseEntity.ok(getFilmQueryImpl.getAllAvailable());
    }
}
