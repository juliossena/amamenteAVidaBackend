package com.julio.amamenteAVida.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julio.amamenteAVida.exception.DataIntegrityException;
import com.julio.amamenteAVida.external.dto.ClientDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseBaseDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseClientDTO;
import com.julio.amamenteAVida.external.entity.Client;
import com.julio.amamenteAVida.external.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private BCryptPasswordEncoder pe;


    public ResponseBaseDTO createNewClient(final ClientDTO client) {
        final Optional<Client> searchClient = repository.findByEmail(client.getEmail());
        if (searchClient.isPresent()) {
            throw new DataIntegrityException("Esse e-mail já está cadastrado");
        }

        client.setPassword(pe.encode(client.getPassword()));
        final Client clientEntity = new Client(client);
        return new ResponseClientDTO(repository.save(clientEntity));
    }
}
