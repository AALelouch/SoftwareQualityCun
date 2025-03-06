package com.api.film.service.film;

import com.api.film.exception.NotFoundException;
import com.api.film.mapper.FilmMapper;
import com.api.film.entity.Film;
import com.api.film.repository.FilmRepository;
import com.api.film.response.FilmResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class GetFilmQueryImplTest {

    GetFilmQuery getFilmQueryImpl;

    @Mock
    FilmRepository filmRepository;

    @Mock
    FilmMapper filmMapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        getFilmQueryImpl = new GetFilmQueryImpl(filmRepository, filmMapper);
    }

    @Test
    void getAll() {
        List<FilmResponse> filmResponseList = List.of(
                new FilmResponse(1L, "FilmOne", "unassigned", null),
                new FilmResponse(2L, "FilmTwo", "unassigned", null));

        List<Film> films = List.of(
                new Film(1L, "FilmOne", "unassigned"),
                new Film(2L, "FilmTwo", "unassigned")
        );

        Mockito.when(filmRepository.findAll()).thenReturn(films);

        Mockito.when(filmMapper.entitiesToResponses(filmRepository.findAll()))
                .thenReturn(filmResponseList);

        assertEquals(getFilmQueryImpl.getAll(), filmResponseList);
    }

    @Test
    void byId() {
        List<Film> films = List.of(
                new Film(1L, "FilmOne", "unassigned"),
                new Film(2L, "FilmTwo", "unassigned")
        );

        FilmResponse filmResponse = new FilmResponse(1L,
                "FilmOne", "unassigned", null);

        Optional<Film> film = Optional.of(new Film(1L, "FilmOne", "unassigned"));

        Mockito.when(filmRepository.findById(any(Long.class))).thenReturn(film);

        Mockito.when(filmMapper.entityToResponse(any(Film.class))).thenReturn(filmResponse);

        assertEquals(getFilmQueryImpl.byId(1L), filmResponse);
    }

    @Test
    void byIdNotFoundException(){

        List<Film> films = List.of(
                new Film(1L, "FilmOne", "unassigned"),
                new Film(2L, "FilmTwo", "unassigned")
        );

        FilmResponse filmResponse = new FilmResponse(1L,
                "FilmOne", "unassigned", null);

        Optional<Film> film = Optional.of(new Film(1L, "FilmOne", "unassigned"));

        Mockito.when(filmRepository.findById(1L)).thenReturn(film);

        Mockito.when(filmMapper.entityToResponse(any(Film.class))).thenReturn(filmResponse);

        assertThrows(NotFoundException.class, () -> {getFilmQueryImpl.byId(44L);});
    }

    @Test
    void byClientNumber() {

        //Arrange
        String clientNumber = "clientNumber";

        List<FilmResponse> filmResponseList = List.of(
                new FilmResponse(1L, "FilmOne", clientNumber, null),
                new FilmResponse(2L, "FilmTwo", "unassigned", null));

        List<Film> films = List.of(
                new Film(1L, "FilmOne", clientNumber),
                new Film(2L, "FilmTwo", "unassigned")
        );

        Mockito.when(filmRepository.findAllRentedByClientNumber(clientNumber))
                .thenReturn(List.of(films.get(0)));

        Mockito.when(filmMapper.entitiesToResponses(filmRepository.findAllRentedByClientNumber(
                clientNumber))).thenReturn(List.of(filmResponseList.get(0)));

        //Act
        List<FilmResponse> filmsToResponse = getFilmQueryImpl.byClientNumber(clientNumber);

        //Assert
        assertEquals(List.of(filmResponseList.get(0)), filmsToResponse);
    }

    @Test
    void byClientNumberNotFoundException(){

        //Arrange
        String clientNumber = "clientNumber";

        List<FilmResponse> filmResponseList = List.of(
                new FilmResponse(1L, "FilmOne", clientNumber, null),
                new FilmResponse(2L, "FilmTwo", "unassigned", null));

        List<Film> films = List.of(
                new Film(1L, "FilmOne", clientNumber),
                new Film(2L, "FilmTwo", "unassigned")
        );

        Mockito.when(filmRepository.findAllRentedByClientNumber(clientNumber))
                .thenReturn(List.of(films.get(0)));

        Mockito.when(filmMapper.entitiesToResponses(filmRepository.findAllRentedByClientNumber(
                clientNumber))).thenReturn(List.of(filmResponseList.get(0)));

        //Assert
        assertThrows(NotFoundException.class, () -> {getFilmQueryImpl.byClientNumber("incorrect");});
    }

    @Test
    void getAllAvailable() {

        //Arrange
        List<FilmResponse> filmResponseList = List.of(
                new FilmResponse(1L, "FilmOne", "unassigned", null),
                new FilmResponse(2L, "FilmTwo", "unassigned", null));

        List<Film> films = List.of(
                new Film(1L, "FilmOne", "unassigned"),
                new Film(2L, "FilmTwo", "unassigned")
        );

        Mockito.when(filmRepository.findAllAvailable()).thenReturn(films);

        Mockito.when(filmMapper.entitiesToResponses(filmRepository.findAllAvailable()))
                .thenReturn(filmResponseList);

        //Act
        List<FilmResponse> filmResponses = getFilmQueryImpl.getAllAvailable();

        //Assert
        assertEquals(filmResponseList, filmResponses);
    }
}