package com.api.film.kafka;

import com.api.film.service.film.UnassignFilmCommand;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import org.springframework.stereotype.Component;

@Component
public class KafkaReturnListener {
    private final UnassignFilmCommand unassignFilmCommand;

    public KafkaReturnListener(UnassignFilmCommand unassignFilmCommand) {
        this.unassignFilmCommand = unassignFilmCommand;
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "${spring.kafka.topic-return}",
            groupId = "${spring.kafka.group-return}",
            containerFactory = "returnKafkaListenerContainerFactory")
    public void listenerReturn(ReturnFilm filmToReturn) {
        unassignFilmCommand.unassignFilm(filmToReturn);
    }
}
