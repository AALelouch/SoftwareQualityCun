package com.api.film.service.film;

import com.api.film.request.FilmRequest;

/**
 * @see com.api.film.service.film.CreateFilmCommandImpl
 * Description: Interface used for service with the usage of create a new film with the filmRequest
 */
public interface CreateFilmCommand {
    void createFilm(FilmRequest filmRequest);
}
