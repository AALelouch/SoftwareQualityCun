package com.practice.clientapi.service.impl;

import com.movies.commonstomovies.kafka.dto.RequestRent;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import com.practice.clientapi.entity.Client;
import com.practice.clientapi.model.FilmResponse;
import com.practice.clientapi.model.RegisterClient;
import com.practice.clientapi.repository.ClientRepository;
import com.practice.clientapi.service.ClientService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

class ClientServiceImplTest {


    @Mock
    private KafkaTemplate<String, RequestRent> rentKafkaTemplate;

    @Mock
    private KafkaTemplate<String, ReturnFilm> returnKafkaTemplate;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private RestTemplate restTemplate;


    private ClientService clientService;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(restTemplate,returnKafkaTemplate,rentKafkaTemplate,clientRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void registerClient() {
        RegisterClient registerClient = new RegisterClient("testName","testLastName");
        Client client = new Client(1L,"testClientNumber","testName","testLastName");

        Mockito.when(clientRepository.save(client)).thenReturn(client);

        Client clientResponse = clientService.registerClient(registerClient);

        Assertions.assertEquals(client.getFirstName(),clientResponse.getFirstName());
        Assertions.assertEquals(client.getLastName(),clientResponse.getLastName());

    }

    @Test
    void getClients() {
        List<Client> clientList = List.of(
                new Client(1L,"testClientNumber","testName","testLastName"),
                new Client(2L,"testClientNumber2","testName2","testLastName2")
        );

        Mockito.when(clientRepository.findAll()).thenReturn(clientList);

        List<Client> clientListResponse = clientService.getClients();
        Assertions.assertEquals(clientList,clientListResponse);
    }

    @Test
    void getClientByClientNumberOk() {
       Optional <Client> clientOptional = Optional.of(new Client(1L, "testClientNumber", "testName", "testLastName"));
        Mockito.when(clientRepository.findByClientNumber(Mockito.any(String.class))).thenReturn(clientOptional);

        Client clientResponse = clientService.getClientByClientNumber("testClientNumber");

        Assertions.assertEquals(clientResponse.getClientNumber(),clientOptional.get().getClientNumber());

    }

    @Test
    void getClientByClientNumberNotFoundException() {
        Optional <Client> clientOptional = Optional.empty();
        Mockito.when(clientRepository.findByClientNumber(Mockito.any(String.class))).thenReturn(clientOptional);

        Assertions.assertThrows(ResponseStatusException.class, () -> {clientService.getClientByClientNumber("testClientNumber");});

    }

    @Test
    void getRentsMoviesByClientNumberOk() {

        FilmResponse filmResponse = new FilmResponse(1L,"testFilm","testClientNumber", LocalDateTime.now());

        String url = "http://film-api:8081/api/film/rented/testClientNumber";

        Mockito.when(restTemplate.getForObject(url,List.class)).thenReturn(List.of(filmResponse));

        List<FilmResponse> filmResponseList = clientService.getRentsMoviesByClientNumber("testClientNumber");

        Assertions.assertEquals(List.of(filmResponse),filmResponseList);

    }

    @Test
    void getRentsMoviesByClientNumberNotFoundException() {

        String url = "http://film-api:8081/api/film/rented/testClientNumber";

        Mockito.when(restTemplate.getForObject(url,List.class)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(ResponseStatusException.class, () -> {clientService.getRentsMoviesByClientNumber("testClientNumber");});

    }

    @Test
    void returnFilm() {
    }

    @Test
    void rentFilm() {
    }
}