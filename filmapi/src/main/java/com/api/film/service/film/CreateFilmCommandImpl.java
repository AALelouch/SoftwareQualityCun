package com.api.film.service.film;

import com.api.film.mapper.FilmMapper;
import com.api.film.repository.FilmRepository;
import com.api.film.request.FilmRequest;
import org.springframework.stereotype.Service;

/**
 * @see CreateFilmCommand
 * Description: Implement the methods of interface CreateFilmCommand and realize operations related
 * with create records
 *
 * try to jenkins two
 */
@Service
public class CreateFilmCommandImpl implements CreateFilmCommand {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public CreateFilmCommandImpl(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    /**
     * Description: Create a film and save it
     *
     * @param filmRequest contains the values to save the object
     */
    @Override
    public void createFilm(FilmRequest filmRequest) {
        filmRepository.save(filmMapper.requestToEntity(filmRequest));
    }
}
