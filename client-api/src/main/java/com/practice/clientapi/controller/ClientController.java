package com.practice.clientapi.controller;

import com.movies.commonstomovies.kafka.dto.RequestRent;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import com.practice.clientapi.entity.Client;
import com.practice.clientapi.model.FilmResponse;
import com.practice.clientapi.model.RegisterClient;
import com.practice.clientapi.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("${base.path}")
public class ClientController implements IClientController{

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> registerClient ( @Validated @RequestBody RegisterClient registerClient){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.registerClient(registerClient));

    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClients());
    }

    @GetMapping("/{clientNumber}")
    public ResponseEntity<Client> getClientByClientNumber(@PathVariable String clientNumber){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClientByClientNumber(clientNumber));
    }

    @GetMapping("/{clientNumber}/rents")
    public List<FilmResponse> getRentsMoviesByClientNumber( @PathVariable String clientNumber){
        return clientService.getRentsMoviesByClientNumber(clientNumber);
    }

    @PostMapping("/{clientNumber}/return")
    public void returnFilm(@Validated @RequestBody ReturnFilm returnFilm, @PathVariable String clientNumber){
        clientService.returnFilm(returnFilm,clientNumber);
    }


    @PostMapping("/{clientNumber}/rent")
    public void rentFilm(@Validated @RequestBody RequestRent requestRent, @PathVariable String clientNumber) {
        clientService.rentFilm(requestRent,clientNumber);


    }


}
