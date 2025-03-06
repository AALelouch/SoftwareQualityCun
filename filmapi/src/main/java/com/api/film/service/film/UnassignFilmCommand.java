package com.api.film.service.film;

import com.movies.commonstomovies.kafka.dto.ReturnFilm;

/**
 * @see com.api.film.service.film.UnassignFilmCommandImpl
 * Description: Interface used in the service to unassign a film
 */
public interface UnassignFilmCommand {
    void unassignFilm(ReturnFilm name);
}
