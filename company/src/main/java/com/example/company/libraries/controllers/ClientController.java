package com.example.company.libraries.controllers;


import com.example.company.libraries.model.Client;
import com.example.company.libraries.exceptions.ClientNotFoundException;
import com.example.company.libraries.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {
    @Autowired
    private final ClientService clientService;

    @GetMapping("/clients")
    List<Client> getClients() {
        return this.clientService.getClients();
    }

    @DeleteMapping("/clients/{id}")
    String deleteClient(@PathVariable Integer id){
        return this.clientService.deleteClient(id);
    }

    @PostMapping("/clients")
    Client newClient(@RequestBody Client newclient){
        return this.clientService.newClient(newclient);
    }

    @PutMapping("/clients/{id}")
    Client updateClient(@RequestBody Client client,@PathVariable Integer id){
        return this.clientService.updateClient(client,id);
    }

}


@RestControllerAdvice
class ClientNotFoundAdvice {

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(ClientNotFoundException ex) {
        return ex.getMessage();
    }
}
