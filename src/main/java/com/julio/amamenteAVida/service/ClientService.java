package com.julio.amamenteAVida.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julio.amamenteAVida.external.dto.ClientDTO;
import com.julio.amamenteAVida.external.entity.Client;
import com.julio.amamenteAVida.external.repository.ClienteRepository;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private BCryptPasswordEncoder pe;


    public Client createNewClient(final ClientDTO client) {
        client.setPassword(pe.encode(client.getPassword()));
        final Client clientEntity = new Client(client);
        return repository.save(clientEntity);
    }
}
