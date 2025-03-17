package com.example.company.libraries.repositories;

import com.example.company.libraries.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
