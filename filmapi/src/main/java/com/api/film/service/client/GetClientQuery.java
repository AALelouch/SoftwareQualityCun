package com.api.film.service.client;

import com.api.film.model.Client;
import org.springframework.http.ResponseEntity;

/**
 * @see com.api.film.service.client.GetClientQueryImpl
 * Description: Interface used in services with the usage of get clients by the id of the
 * film assigned
 */
public interface GetClientQuery {
    ResponseEntity<Client> byIdFilm(Long idFilm);
}
