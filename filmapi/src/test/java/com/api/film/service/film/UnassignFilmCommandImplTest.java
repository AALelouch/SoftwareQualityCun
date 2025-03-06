package com.api.film.service.film;

import com.api.film.exception.NotFoundException;
import com.api.film.entity.Film;
import com.api.film.repository.FilmRepository;
import com.movies.commonstomovies.calculatetime.CalculateDaysFactory;
import com.movies.commonstomovies.calculatetime.CalculateTimeFactory;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnassignFilmCommandImplTest {

    @Mock
    private FilmRepository filmRepository;
    @Mock
    private RestTemplate restTemplate;

    private UnassignFilmCommand unassignFilmCommandImpl;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        CalculateTimeFactory calculateTimeFactory = new CalculateDaysFactory();
        unassignFilmCommandImpl = new UnassignFilmCommandImpl(filmRepository, calculateTimeFactory, restTemplate);
    }

    @Test
    void unassignFilm() {

        //Arrange
        ReturnFilm returnFilm = new ReturnFilm("FilmOne", "ClientNumber",
                LocalDateTime.of(2022, 11, 2, 0, 0, 0));

        Film filmToSave = new Film(1L, "FilmOne", "unassigned",
                null,
                null);

        List<Film> films = List.of(
                new Film(1L, "FilmOne", "ClientNumber",
                        LocalDateTime.of(2022, 11, 2, 0, 0, 0),
                        null),

                new Film(2L, "FilmTwo", "ClientNumber")
        );

        Mockito.when(filmRepository.findAllRentedByClientNumber("ClientNumber")).thenReturn(films);

        //Act
        unassignFilmCommandImpl.unassignFilm(returnFilm);

        //Assert
        Mockito.verify(filmRepository, Mockito.times(1)).save(filmToSave);
    }

    @Test
    void unassignFilmNotFoundException() {
        ReturnFilm returnFilm = new ReturnFilm("FilmOne", "ClientNumberIncorrect");
        Film filmToSave = new Film(1L, "FilmOne", "ClientNumber");

        List<Film> films = List.of(
                new Film(1L, "FilmOne", "ClientNumber"),
                new Film(2L, "FilmTwo", "ClientNumber")
        );

        Mockito.when(filmRepository.findAllRentedByClientNumber("ClientNumber")).thenReturn(films);

        assertThrows(NotFoundException.class, () -> unassignFilmCommandImpl.unassignFilm(returnFilm));
    }
}