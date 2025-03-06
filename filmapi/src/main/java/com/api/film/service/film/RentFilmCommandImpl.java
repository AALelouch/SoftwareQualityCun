package com.api.film.service.film;

import com.api.film.exception.NotFoundException;
import com.api.film.entity.Film;
import com.api.film.repository.FilmRepository;
import com.movies.commonstomovies.kafka.dto.RequestRent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Service
public class RentFilmCommandImpl implements RentFilmCommand {

    private final FilmRepository filmRepository;

    public RentFilmCommandImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public void rentFilm(RequestRent requestRent) {

        Film film = filmRepository.findAllAvailable().stream().filter(
                filmToRent -> filmToRent.getName().equals(requestRent.getNameFilm()))
                .findFirst().orElseThrow(() ->
                        new NotFoundException("Films doesn't found or not exist"));

        UnaryOperator<Film> rentFilmFunction = filmWithRentData -> {
            filmWithRentData.setClientNumber(requestRent.getClientNumber());
            filmWithRentData.setRentDate(requestRent.getRentDate());
            return filmWithRentData;
        };

        filmRepository.save(rentFilmFunction.apply(film));
    }
}
