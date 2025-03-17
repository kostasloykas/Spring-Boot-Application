package com.example.company.client;

import com.example.company.libraries.controllers.ClientController;
import com.example.company.libraries.exceptions.ClientNotFoundException;
import com.example.company.libraries.model.Client;
import com.example.company.libraries.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import static org.assertj.core.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import lombok.val;

import java.awt.color.ICC_Profile;
import java.util.List;
import java.util.Optional;


@WebMvcTest(ClientController.class)
public class ClientControllerTests {

    @Autowired
    private MockMvc mockMvc; //For httpRequests without running a real server

    @MockitoBean // Mocks ClientService so it's available for injection
    private ClientService clientService;

    @Test
    @SneakyThrows
    void getClients() {
        Client client = Client.builder().withFirstName("Xristos").withLastName("Papadopoulos").withId(1).build();
        when(clientService.getClients()).thenReturn(List.of(client));

        val response= mockMvc.perform(get("/clients")).andExpect(status().is(200)).andReturn();
        val content = response.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Client> clients = objectMapper.readValue(content, new TypeReference<List<Client>>() {});

        assertThat(clients).isNotNull();
        assertThat(clients).size().isEqualTo(1);
        assertThat(clients).contains(client);
    }

    @Test
    @SneakyThrows
    void deleteExistingClient(){
        when(clientService.deleteClient(1)).thenReturn("Client Deleted");

        val response= mockMvc.perform(delete("/clients/1")).andExpect(status().is(200)).andReturn();
        val content = response.getResponse().getContentAsString();

        assertThat(content).isEqualTo("Client Deleted");
    }

    @Test
    @SneakyThrows
    void deleteNotExistingClient(){
        when(clientService.deleteClient(1)).thenThrow(new ClientNotFoundException(1));

        val response= mockMvc.perform(delete("/clients/1")).andExpect(status().is(404)).andReturn();
        val content = response.getResponse().getContentAsString();

        assertThat(content).isEqualTo("Could not find Client 1");
    }

    @Test
    @SneakyThrows
    void newClient(){
        Client client = Client.builder().withFirstName("Xristos").withLastName("Papadopoulos").withId(1).build();
        when(clientService.newClient(client)).thenReturn(client);

        Gson gson = new Gson();
        val client_content = gson.toJson(client);

        val response= mockMvc.perform(post("/clients").content(client_content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200)).andReturn();
        val content = response.getResponse().getContentAsString();

        Client newClient = gson.fromJson(content, Client.class);

        assertThat(newClient).isEqualTo(client);
    }

    @Test
    void updateClient(){
    }
}

