package com.api.film.controller.impl;

import com.api.film.service.film.CreateFilmCommand;
import com.api.film.service.film.GetFilmQuery;
import com.api.film.request.FilmRequest;
import com.api.film.response.FilmResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FilmControllerImpl.class, properties = "spring.cloud.config.fail-fast=false")
class FilmControllerImplTest {

    @MockBean
    private CreateFilmCommand createFilmCommandImpl;

    @MockBean
    private GetFilmQuery getFilmQueryImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFilm() throws Exception {

        //Arrange
        FilmRequest filmRequest = new FilmRequest("FilmOne");

        //Act
        mockMvc.perform(post("/api/film")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void getFilmById() throws Exception {

        //Arrange
        FilmResponse filmResponse = new FilmResponse(
                1L,
                "filmName",
                "filmName",
                LocalDateTime.of(2022, 11, 1, 0, 0 ,0));

        //When
        when(getFilmQueryImpl.byId(any(Long.class))).thenReturn(filmResponse);

        //Assert
        mockMvc.perform(get("/api/film/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(filmResponse)));

    }

    @Test
    void getFilmsRentedByClientNumber() throws Exception {

        //Arrange
        List<FilmResponse> filmResponseList = List.of(
                new FilmResponse(1L,
                        "filmNameOne",
                        "clientNumber",
                        LocalDateTime.of(2022, 11, 1, 0, 0 ,0)),
                new FilmResponse(
                        1L,
                        "filmNameTwo",
                        "clientNumber",
                        LocalDateTime.of(2022, 11, 1, 0, 0 ,0))
        );

        //When
        when(getFilmQueryImpl.byClientNumber(any(String.class))).thenReturn(filmResponseList);

        //Assert
        mockMvc.perform(get("/api/film/rented/clientNumber")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(filmResponseList)));

    }

    @Test
    void getAllFilms() throws Exception {

        //Arrange
        List<FilmResponse> filmResponseList = List.of(
                new FilmResponse(1L,
                        "filmNameOne",
                        "clientNumber",
                        LocalDateTime.of(2022, 11, 1, 0, 0 ,0)),
                new FilmResponse(
                        1L,
                        "filmNameTwo",
                        "clientNumber",
                        LocalDateTime.of(2022, 11, 1, 0, 0 ,0))
        );

        //When
        when(getFilmQueryImpl.getAll()).thenReturn(filmResponseList);

        //Assert
        mockMvc.perform(get("/api/film/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(filmResponseList)));
    }

    @Test
    void getAllFilmsAvailable() throws Exception {

        //Arrange
        List<FilmResponse> filmResponseList = List.of(
                new FilmResponse(1L,
                        "filmNameOne",
                        "unassigned",
                        LocalDateTime.of(2022, 11, 1, 0, 0 ,0)),
                new FilmResponse(
                        1L,
                        "filmNameTwo",
                        "unassigned",
                        LocalDateTime.of(2022, 11, 1, 0, 0 ,0))
        );

        //When
        when(getFilmQueryImpl.getAllAvailable()).thenReturn(filmResponseList);

        //Assert
        mockMvc.perform(get("/api/film/available")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(filmResponseList)));
    }
}