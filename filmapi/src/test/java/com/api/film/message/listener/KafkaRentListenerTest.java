package com.api.film.message.listener;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.api.film.kafka.KafkaRentListener;
import com.api.film.service.film.RentFilmCommand;
import com.movies.commonstomovies.kafka.dto.RequestRent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KafkaRentListener.class})
@ExtendWith(SpringExtension.class)
class KafkaRentListenerTest {
    @Autowired
    private KafkaRentListener kafkaRentListener;

    @MockBean
    private RentFilmCommand rentFilmCommand;

    @Test
    void testListenerRent() {

        //Arrange
        doNothing().when(rentFilmCommand).rentFilm((RequestRent) any());
        RequestRent requestRent = new RequestRent("filmName", "clientNumber");

        //Act
        kafkaRentListener.listenerRent(requestRent);

        //Assert
        verify(rentFilmCommand).rentFilm((RequestRent) any());
    }
}

