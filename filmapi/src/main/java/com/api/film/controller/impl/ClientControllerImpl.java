package com.api.film.controller.impl;

import com.api.film.controller.ClientController;
import com.api.film.model.Client;
import com.api.film.service.client.GetClientQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/film/client")
public class ClientControllerImpl implements ClientController {

    private final GetClientQuery getClientQuery;

    public ClientControllerImpl(GetClientQuery getClientQuery) {
        this.getClientQuery = getClientQuery;
    }

    @GetMapping("/{idFilm}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Client> getClientByIdFilm(@PathVariable Long idFilm) {
        return getClientQuery.byIdFilm(idFilm);
    }
}
