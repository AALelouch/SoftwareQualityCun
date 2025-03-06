package com.api.film.service.film;

import com.api.film.exception.NotFoundException;
import com.api.film.entity.Film;
import com.api.film.repository.FilmRepository;
import com.movies.commonstomovies.kafka.dto.RequestRent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentFilmCommandImplTest {

    @Mock
    private FilmRepository filmRepository;

    private RentFilmCommand rentFilmCommandImpl;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this).close();
        rentFilmCommandImpl = new RentFilmCommandImpl(filmRepository);
    }

    @Test
    void rentFilm() {

        RequestRent requestRent = new RequestRent("FilmOne", "ClientNumber");
        Film filmToSave = new Film(1L, "FilmOne", "ClientNumber");

        List<Film> films = List.of(
                new Film(1L, "FilmOne", "unassigned"),
                new Film(2L, "FilmTwo", "unassigned")
        );

        Mockito.when(filmRepository.findAllAvailable()).thenReturn(films);

        rentFilmCommandImpl.rentFilm(requestRent);

        Mockito.verify(filmRepository, Mockito.times(1)).save(filmToSave);

    }

    @Test
    void rentFilmNotFoundException() {

        RequestRent requestRent = new RequestRent(
                "Non-Existing Film", "ClientNumber");

        Film filmToSave = new Film(1L, "FilmOne", "ClientNumber");

        List<Film> films = List.of(
                new Film(1L, "FilmOne", "unassigned"),
                new Film(2L, "FilmTwo", "unassigned")
        );

        Mockito.when(filmRepository.findAllAvailable()).thenReturn(films);

        assertThrows(NotFoundException.class, () -> rentFilmCommandImpl.rentFilm(requestRent));
    }
}