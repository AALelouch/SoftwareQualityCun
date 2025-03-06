package com.api.film.mapper;

import com.api.film.entity.Film;
import com.api.film.request.FilmRequest;
import com.api.film.response.FilmResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Description: A class to map the different classes related to film and his response and request
 */
@Mapper(componentModel = "spring")
public interface FilmMapper {

    /**
     * Description: map a film request to entity
     *
     * @param filmRequest is the request to create a new entity
     * @return An object of class Film used in storage the data
     */
    Film requestToEntity(FilmRequest filmRequest);

    /**
     * Description: map a film entity to response
     *
     * @param film is the entity to map a response to show the data to the user
     * @return A film response for show the data to the user
     */
    FilmResponse entityToResponse(Film film);

    List<FilmResponse> entitiesToResponses(List<Film> films);
}
