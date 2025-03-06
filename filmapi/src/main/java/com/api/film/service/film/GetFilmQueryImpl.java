package com.api.film.service.film;

import com.api.film.exception.NotFoundException;
import com.api.film.mapper.FilmMapper;
import com.api.film.repository.FilmRepository;
import com.api.film.response.FilmResponse;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Service
public class GetFilmQueryImpl implements GetFilmQuery {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public GetFilmQueryImpl(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    @Override
    public List<FilmResponse> getAll() {

        return filmMapper.entitiesToResponses(filmRepository.findAll());
    }

    @Override
    public FilmResponse byId(Long id) {

        return filmMapper.entityToResponse(
                filmRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Not Found")));
    }

    @Override
    public List<FilmResponse> byClientNumber(@NotBlank String clientNumber) {

        List<FilmResponse> films = filmMapper.entitiesToResponses(filmRepository
                .findAllRentedByClientNumber(clientNumber));

        if (Objects.requireNonNull(films).isEmpty()) {
            throw new NotFoundException("Movies not found with this client number");
        }

        return films;
    }

    @Override
    public List<FilmResponse> getAllAvailable() {

        return filmMapper.entitiesToResponses(filmRepository.findAllAvailable());
    }
}
