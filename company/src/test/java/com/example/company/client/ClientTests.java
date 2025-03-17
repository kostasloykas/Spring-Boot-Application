package com.example.company.client;

import com.example.company.libraries.model.Client;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class ClientTests {

    @Test
    void createClient(){
        Client client = Client.builder().withFirstName("Kostas").withLastName("Loukas").withId(555).build();

        assertThat(client.getFirstName()).isEqualTo("Kostas");
        assertThat(client.getLastName()).isEqualTo("Loukas");
        assertThat(client.getId()).isEqualTo(555);
    }
}
