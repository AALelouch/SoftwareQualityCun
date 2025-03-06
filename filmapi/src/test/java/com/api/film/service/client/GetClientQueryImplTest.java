package com.api.film.service.client;

import com.api.film.exception.NotFoundException;
import com.api.film.model.Client;
import com.api.film.entity.Film;
import com.api.film.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


class GetClientQueryImplTest {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private RestTemplate restTemplate;

    private GetClientQuery getClientQuery;

    @Value(value = "${spring.client.url}")
    private String uri;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        getClientQuery = new GetClientQueryImpl(filmRepository, restTemplate);
    }

    @Test
    void byIdFilm() {

        //Arrange
        Client client = new Client(
                1L, "ClientNumber", "FirstName", "LastName");

        Optional<Film> film = Optional.of(
                new Film(1L, "NameFilm", "ClientNumber"));

        Mockito.when(filmRepository.findById(any(Long.class))).thenReturn(film);

        uri = uri + "ClientNumber";

        Mockito.when(restTemplate.getForObject(uri, Client.class)).thenReturn(client);

        //Act
        ResponseEntity<Client> clientResponseEntity = getClientQuery.byIdFilm(1L);

        //Assert
        assertEquals(new ResponseEntity<>(client, HttpStatus.OK), clientResponseEntity);
    }

    @Test
    void byIdFilmNotFoundException() {

        //Arrange
        Client client = new Client(
                1L, "ClientNumber", "FirstName", "LastName");

        Optional<Film> film = Optional.of(
                new Film(1L, "NameFilm", "ClientNumber"));

        uri = uri + "ClientNumber";

        Mockito.when(filmRepository.findById(1L)).thenReturn(film);

        Mockito.when(restTemplate.getForObject(uri, Client.class)).thenReturn(client);


        //Assert
        assertThrows(NotFoundException.class, () -> getClientQuery.byIdFilm(11L));
    }
}