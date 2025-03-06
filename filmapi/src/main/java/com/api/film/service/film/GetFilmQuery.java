package com.api.film.service.film;

import com.api.film.response.FilmResponse;

import java.util.List;

/**
 * @see com.api.film.service.film.GetFilmQueryImpl
 * Description: Interface using for declare methods related to get films with different parameters
 */
public interface GetFilmQuery {

    List<FilmResponse> getAll();

    FilmResponse byId(Long id);

    List<FilmResponse> byClientNumber(String clientNumber);

    List<FilmResponse> getAllAvailable();
}
