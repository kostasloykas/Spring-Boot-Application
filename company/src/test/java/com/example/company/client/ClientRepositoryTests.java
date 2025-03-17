package com.example.company.client;

import com.example.company.libraries.model.Client;
import com.example.company.libraries.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
public class ClientRepositoryTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void createClient(){
        Client client = Client.builder().withFirstName("Kostas").withLastName("Loukas").build();

        // Save client
        client = clientRepository.save(client);

        // Find client
        Client found_client = clientRepository.findById(client.getId()).orElse(null);

        assertThat(found_client).isNotNull();
        assertThat(found_client.getFirstName()).isEqualTo("Kostas");
        assertThat(found_client.getLastName()).isEqualTo("Loukas");

        System.out.println(found_client.toString() + " Added successfully in the repository");
    }

    @Test
    void deleteClient(){
        Client client = Client.builder().withFirstName("Kostas").withLastName("Loukas").build();

        // Save client
        client = clientRepository.save(client);

        // Delete client
        clientRepository.deleteById(client.getId());

        // Find Client
        Client found_client = clientRepository.findById(client.getId()).orElse(null);

        assertThat(found_client).isNull();

        System.out.println(client.toString() + "Removed successfully from the repository");
    }

    @Test
    void updateClient(){
        Client client = Client.builder().withFirstName("Kostas").withLastName("Loukas").build();

        // Save client
        client = clientRepository.save(client);
        System.out.println(client.toString() + "Before Update");

        // Find Client
        Client found_client = clientRepository.findById(client.getId()).orElse(null);

        assertThat(found_client).isNotNull();


        //Update client
        found_client.setFirstName("Xristina");
        found_client.setLastName("Tatu");

        Client updatedClient = clientRepository.findById(found_client.getId()).map(existingClient -> {
            existingClient.setFirstName(found_client.getFirstName());
            existingClient.setLastName(found_client.getLastName());
            return this.clientRepository.save(existingClient);}).orElse(null);

        assertThat(updatedClient).isNotNull();
        assertThat(updatedClient.getId()).isEqualTo(client.getId());
        assertThat(updatedClient.getFirstName()).isEqualTo("Xristina");
        assertThat(updatedClient.getLastName()).isEqualTo("Tatu");

        System.out.println(updatedClient.toString() + "Updated successfully");
    }
}
