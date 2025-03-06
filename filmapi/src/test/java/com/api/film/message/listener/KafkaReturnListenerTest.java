package com.api.film.message.listener;

import com.api.film.kafka.KafkaReturnListener;
import com.api.film.service.film.UnassignFilmCommand;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {KafkaReturnListener.class})
@ExtendWith(SpringExtension.class)
class KafkaReturnListenerTest {

    @Autowired
    private KafkaReturnListener kafkaReturnListener;

    @MockBean
    private UnassignFilmCommand unassignFilmCommand;

    @Test
    void listenerReturn() {

        //Arrange
        doNothing().when(unassignFilmCommand).unassignFilm((ReturnFilm) any());
        ReturnFilm returnFilm = new ReturnFilm("filmName", "clientNumber");

        //Act
        kafkaReturnListener.listenerReturn(returnFilm);

        //Assert
        verify(unassignFilmCommand, Mockito.times(1)).unassignFilm((ReturnFilm) any());

    }
}