package com.julio.amamenteAVida.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julio.amamenteAVida.exception.DataIntegrityException;
import com.julio.amamenteAVida.exception.ObjectNotFoundException;
import com.julio.amamenteAVida.external.dto.ClientDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseBaseDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseClientDTO;
import com.julio.amamenteAVida.external.entity.Client;
import com.julio.amamenteAVida.external.entity.CodeValidationClient;
import com.julio.amamenteAVida.external.repository.ClientRepository;
import com.julio.amamenteAVida.external.repository.CodeValidationClientRepository;
import com.julio.amamenteAVida.utils.CodeValidation;

@Service
public class ClientService {

    private final Integer SIZE_CODE_VALIDATION = 4;
    private final Integer MINUTES_CODE_EXPIRATION = 1440;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private CodeValidationClientRepository codeValidationClientRepository;

    @Autowired
    private EmailService emailService;

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

    public void sendCodeNewPassword(final String email) {
        final Optional<Client> client = repository.findByEmail(email);
        if (!client.isPresent()) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        final LocalDateTime dateExpiration = LocalDateTime.now()
            .plusMinutes(MINUTES_CODE_EXPIRATION);
        final String code = CodeValidation.newPassword(SIZE_CODE_VALIDATION);

        final CodeValidationClient codeValidation =
                new CodeValidationClient(client.get(), code, dateExpiration);

        codeValidationClientRepository.save(codeValidation);


        emailService.sendNewPasswordEmail(client.get(), code);

    }
}
