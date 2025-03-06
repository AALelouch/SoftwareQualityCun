package com.practice.clientapi.controller;

import com.movies.commonstomovies.kafka.dto.RequestRent;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import com.practice.clientapi.entity.Client;
import com.practice.clientapi.model.FilmResponse;
import com.practice.clientapi.model.RegisterClient;
import com.practice.clientapi.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;


class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    ClientController clientController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerClient() {
        Client client = new Client(1L,"testClientNumber","testName","testLastName");

        Mockito.when(clientService.registerClient(Mockito.any(RegisterClient.class))).thenReturn(client);

        ResponseEntity<Client> registerClient = clientController.registerClient(new RegisterClient("testName","testLastName"));

        Assertions.assertNotNull(registerClient);
        Assertions.assertEquals(client.getFirstName(),registerClient.getBody().getFirstName());


    }

    @Test
    void getClients() {
        List<Client> clientList = List.of(
                new Client(1L,"testClientNumber","testName","testLastName"),
                new Client(2L,"testClientNumber2","testName2","testLastName2")
        );

        Mockito.when(clientService.getClients()).thenReturn(clientList);

        ResponseEntity<List<Client>> listClientResponse = clientController.getClients();

        Assertions.assertNotNull(listClientResponse);
        Assertions.assertEquals(clientList,listClientResponse.getBody());
    }

    @Test
    void getClientByClientNumber() {
        Client client = new Client(1L,"testClientNumber","testName","testLastName");

        Mockito.when(clientService.getClientByClientNumber(Mockito.any())).thenReturn(client);

        ResponseEntity<Client> clientResult = clientController.getClientByClientNumber("testClientNumber");

        Assertions.assertNotNull(clientResult);
        Assertions.assertEquals(client.getFirstName(),clientResult.getBody().getFirstName());
    }

    @Test
    void getRentsMoviesByClientNumber() {

        FilmResponse filmResponse = new FilmResponse(1L,"testFilm","testClientNumber");

        Mockito.when(clientService.getRentsMoviesByClientNumber(Mockito.anyString())).thenReturn(List.of(filmResponse));

       List<FilmResponse> listResponseEntity = clientController.getRentsMoviesByClientNumber("testClientNumber");

        Assertions.assertNotNull(listResponseEntity);
        Assertions.assertEquals(List.of(filmResponse),listResponseEntity);


    }

    @Test
    void returnFilm() {
        ReturnFilm returnFilm = new ReturnFilm("testFilm","testClientNumber");
        clientController.returnFilm(returnFilm,"testClientNumber");
        Mockito.verify(clientService,Mockito.times(1)).returnFilm(returnFilm,"testClientNumber");
    }

    @Test
    void rentFilm() {
        RequestRent requestRent = new RequestRent("testFilm","testClientNumber");
        clientController.rentFilm(requestRent,"testClientNumber");
        Mockito.verify(clientService,Mockito.times(1)).rentFilm(requestRent,"testClientNumber");
    }
}