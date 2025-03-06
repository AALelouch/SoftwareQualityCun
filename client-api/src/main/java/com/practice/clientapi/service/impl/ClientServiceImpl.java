package com.practice.clientapi.service.impl;

import com.movies.commonstomovies.kafka.dto.RequestRent;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import com.practice.clientapi.entity.Client;
import com.practice.clientapi.model.FilmResponse;
import com.practice.clientapi.model.RegisterClient;
import com.practice.clientapi.repository.ClientRepository;
import com.practice.clientapi.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Value("${spring.kafka.topic-assign}")
    private String rentTopicName;

    @Value("${spring.kafka.topic-return}")
    private String returnTopicName;

    private final KafkaTemplate<String, ReturnFilm> returnKafkaTemplate;
    private final KafkaTemplate<String, RequestRent> rentKafkaTemplate;
    private RestTemplate restTemplate;

    private ClientRepository clientRepository;

    public ClientServiceImpl(RestTemplate restTemplate, KafkaTemplate<String, ReturnFilm> returnKafkaTemplate, KafkaTemplate<String, RequestRent> rentKafkaTemplate, ClientRepository clientRepository) {
        this.restTemplate = restTemplate;
        this.returnKafkaTemplate = returnKafkaTemplate;
        this.rentKafkaTemplate = rentKafkaTemplate;
        this.clientRepository = clientRepository;
    }


    @Override
    public Client registerClient(RegisterClient registerClient) {

        Client client = Client.builder()
                .firstName(registerClient.getFirstName())
                .lastName(registerClient.getLastName())
                .clientNumber(generateClientNumber())
                .build();
        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientByClientNumber(String clientNumber) {
        Optional<Client> optionalClient = clientRepository.findByClientNumber(clientNumber);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Client not found"
        );
    }

    @Override
    public List<FilmResponse> getRentsMoviesByClientNumber(String clientNumber) {

        String url = "http://film-api:8081/api/film/rented/" + clientNumber;

        try {
            return restTemplate.getForObject(url, List.class);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "This client does not have rented films"
            );
        }


    }


    @Override
    public void returnFilm(ReturnFilm returnFilm, String clientNumber) {
        returnFilm.setClientNumber(clientNumber);
        returnKafkaTemplate.send(returnTopicName, returnFilm);
    }


    @Override
    public void rentFilm(RequestRent requestRent, String clientNumber) {
        requestRent.setClientNumber(clientNumber);
        rentKafkaTemplate.send(rentTopicName, requestRent);

    }

    private String generateClientNumber() {

        String longClientNumber = UUID.randomUUID().toString();
        String[] clientNumber = longClientNumber.split("-");
        return clientNumber[0];

    }

}
