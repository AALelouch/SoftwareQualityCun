package com.api.film.kafka;

import com.api.film.service.film.RentFilmCommand;
import com.movies.commonstomovies.kafka.dto.RequestRent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaRentListener {

    private final RentFilmCommand filmCommandImpl;

    public KafkaRentListener(RentFilmCommand filmCommandImpl) {
        this.filmCommandImpl = filmCommandImpl;
    }

    @KafkaListener(topics = "${spring.kafka.topic-rent}",
            groupId = "${spring.kafka.group-rent}",
            containerFactory = "rentKafkaListenerContainerFactory")
    public void listenerRent(RequestRent requestRent) {
        filmCommandImpl.rentFilm(requestRent);
    }
}
