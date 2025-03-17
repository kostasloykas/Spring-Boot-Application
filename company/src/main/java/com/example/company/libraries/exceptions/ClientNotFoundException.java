package com.example.company.libraries.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Integer id) {
        super("Could not find Client " + id);
    }
}
