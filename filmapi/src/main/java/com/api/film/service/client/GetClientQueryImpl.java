package com.api.film.service.client;

import com.api.film.exception.NotFoundException;
import com.api.film.model.Client;
import com.api.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @see GetClientQuery
 * Description: Implement the methods of interface GetClientQuery and realize operations related
 * with get information
 */
@Service
public class GetClientQueryImpl implements GetClientQuery {

    private final FilmRepository filmRepository;

    private final RestTemplate restTemplate;

    @Value(value = "${spring.client.url}")
    private String urlToFindClient;

    public GetClientQueryImpl(FilmRepository filmRepository, RestTemplate restTemplate) {
        this.filmRepository = filmRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * Description: Method used to get the information of the film using the id of film assigned
     *
     * @param idFilm used to find a client to expose the client number and send it to the client
     *               api
     * @return The information of the client assigned to the film in a Client object
     * @throws NotFoundException if the film with the id doesn't exist
     */
    @Override
    public ResponseEntity<Client> byIdFilm(Long idFilm) {

        String urlWithClientNumber = urlToFindClient + filmRepository.findById(idFilm)
                .orElseThrow(() -> new NotFoundException("Not found")).getClientNumber();

        Client client;

        try {
            client = restTemplate.getForObject(urlWithClientNumber, Client.class);
        } catch (RuntimeException e) {
            throw new NotFoundException("Client Not Found");
        }

        return ResponseEntity.ok(client);
    }
}
