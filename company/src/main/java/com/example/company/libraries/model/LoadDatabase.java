package com.example.company.libraries.model;

import com.example.company.libraries.repositories.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  LoadDatabase {

    @Bean
    int initDatabase(ClientRepository clientRepository) {
        clientRepository.save(Client.builder().withFirstName("Katerina").withLastName("Louka").build());
        clientRepository.save(Client.builder().withFirstName("Xristos").withLastName("Giannakopoulos").build());
        clientRepository.save(Client.builder().withFirstName("Petros").withLastName("Sloukas").build());

        System.out.println("-----------------------------------------".repeat(3));
        System.out.println(clientRepository.findAll().toString());
        System.out.println("Initialization of the Database Finished");
        System.out.println("-----------------------------------------".repeat(3));
        return 1;
    }
}
