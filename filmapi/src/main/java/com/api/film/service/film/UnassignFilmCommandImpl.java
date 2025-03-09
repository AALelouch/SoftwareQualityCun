package com.api.film.service.film;

import com.api.film.exception.NotFoundException;
import com.api.film.entity.Film;
import com.api.film.repository.FilmRepository;
import com.movies.commonstomovies.calculatetime.CalculateTimeFactory;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import com.movies.commonstomovies.notification.model.ReturnFilmNotificationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.UnaryOperator;

@Service
public class UnassignFilmCommandImpl implements UnassignFilmCommand {

    private final FilmRepository filmRepository;
    private final CalculateTimeFactory calculateTimeFactory;
    private final RestTemplate restTemplate;

    private final String URLTOEMAILNOTIFICATION =
            "http://babynotificationhub:8084/api/notification/returned-film";

    private final UnaryOperator<Film> resetDataOfFilm = film -> {
        film.setClientNumber("unassigned");
        film.setRentDate(null);
        film.setReturnDate(null);
        return film;
    };

    public UnassignFilmCommandImpl(FilmRepository filmRepository,
                                   CalculateTimeFactory calculateTimeFactory,
                                   RestTemplate restTemplate) {
        this.filmRepository = filmRepository;
        this.calculateTimeFactory = calculateTimeFactory;
        this.restTemplate = restTemplate;
    }

    @Override
    public void unassignFilm(ReturnFilm requestReturn) {

        filmRepository.save(resetDataOfFilm.apply(buildFilmToReturn(requestReturn)));
    }

    private Film buildFilmToReturn(ReturnFilm returnFilm){

        Film film = filmRepository
                .findAllRentedByClientNumber(returnFilm.getClientNumber())
                .stream()
                .filter(filmToFind -> filmToFind.getName().equals(returnFilm.getFilmName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Films doesn't found or not exist"));

        film.setReturnDate(returnFilm.getReturnDate());

        return film;
    }

    private ReturnFilmNotificationModel buildReturnFilmNotification(Film filmToReturn){

        ReturnFilmNotificationModel returnFilmNotificationModel =
                new ReturnFilmNotificationModel();

        returnFilmNotificationModel.setClientNumber(filmToReturn.getClientNumber());
        returnFilmNotificationModel.setName(filmToReturn.getName());
        returnFilmNotificationModel.setRentDate(filmToReturn.getRentDate().toString());
        returnFilmNotificationModel.setReturnDate(filmToReturn.getReturnDate().toString());
        returnFilmNotificationModel.setDaysRented(calculateTimeFactory
                .create().calculate(filmToReturn.getRentDate(), filmToReturn.getReturnDate()));


        return returnFilmNotificationModel;
    }
}
