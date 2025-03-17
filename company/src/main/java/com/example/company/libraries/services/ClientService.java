package com.example.company.libraries.services;

import com.example.company.libraries.model.Client;
import com.example.company.libraries.exceptions.ClientNotFoundException;
import com.example.company.libraries.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public List<Client> getClients() {
        return this.clientRepository.findAll();
    }

    public Client newClient(Client newclient){
//        TODO: Here must check if client is already in the database
        return this.clientRepository.save(newclient);
    }

    public Client updateClient(Client newclient, Integer id){
        return this.clientRepository.findById(id).map(client -> {
            client.setFirstName(newclient.getFirstName());
            client.setLastName(newclient.getLastName());
            return this.clientRepository.save(client);
        }).orElseThrow(() -> new ClientNotFoundException(id));

    }

    public String deleteClient(Integer id){
        this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException((id)));
        this.clientRepository.deleteById(id);
        return "Client Deleted";
    }
}

