package com.api.film.service.film;

import com.api.film.mapper.FilmMapper;
import com.api.film.entity.Film;
import com.api.film.repository.FilmRepository;
import com.api.film.request.FilmRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

class CreateFilmCommandImplTest {

    private CreateFilmCommand createFilmCommand;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmMapper filmMapper;

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.openMocks(this).close();
        createFilmCommand = new CreateFilmCommandImpl(filmRepository, filmMapper);
    }

    @Test
    void createFilm() {

        //Arrange
        FilmRequest filmRequest = new FilmRequest("filmOne");
        Film film = new Film(1L, "filmOne", "unassigned");

        Mockito.when(filmMapper.requestToEntity(any(FilmRequest.class))).thenReturn(film);

        //Act
        createFilmCommand.createFilm(filmRequest);

        //Assert
        Mockito.verify(filmRepository, Mockito.times(1)).save(film);
    }
}