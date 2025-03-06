package com.api.film.repository;

import com.api.film.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description: Repository used to storage the data related with films and realize the operation
 * related to query to get information with determinate params
 */
public interface FilmRepository extends JpaRepository<Film, Long> {

    /**
     * Description: find all films rented of determinate user using the client number
     *
     * @param clientNumber the number used to filter the data depending on the client
     * @return a List of all films rented by a client
     */
    List<Film> findAllRentedByClientNumber(String clientNumber);

    /**
     * Description: find a film using the name
     *
     * @param name used to filter the data using the name of the film
     * @return Null if the film doesn't exist or a Film if the name is found
     */
    List<Film> findByName(String name);

    /**
     * Description: find all films available to rent
     *
     * @return a List of films available
     */
    @Query("select f from Film f where f.clientNumber = 'unassigned'")
    List<Film> findAllAvailable();
}
