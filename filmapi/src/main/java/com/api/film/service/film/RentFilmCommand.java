package com.api.film.service.film;

import com.movies.commonstomovies.kafka.dto.RequestRent;

/**
 * @see com.api.film.service.film.RentFilmCommandImpl
 * Description: Interface used in service for rent a film
 */
public interface RentFilmCommand {
    void rentFilm(RequestRent requestRent);
}
