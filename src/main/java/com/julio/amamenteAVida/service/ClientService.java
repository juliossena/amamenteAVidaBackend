package com.julio.amamenteAVida.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julio.amamenteAVida.exception.DataIntegrityException;
import com.julio.amamenteAVida.exception.ObjectNotFoundException;
import com.julio.amamenteAVida.external.dto.ClientDTO;
import com.julio.amamenteAVida.external.dto.PasswordDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseBaseDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseClientDTO;
import com.julio.amamenteAVida.external.entity.Client;
import com.julio.amamenteAVida.external.entity.CodeValidationClient;
import com.julio.amamenteAVida.external.repository.ClientRepository;
import com.julio.amamenteAVida.external.repository.CodeValidationClientRepository;
import com.julio.amamenteAVida.security.JWTUtil;
import com.julio.amamenteAVida.security.UserSS;
import com.julio.amamenteAVida.utils.CodeValidation;

@Service
public class ClientService {

    private final Integer SIZE_CODE_VALIDATION = 6;
    private final Integer MINUTES_CODE_EXPIRATION = 1440;
    private final Integer SIZE_MAX_ATTEMPTS = 5;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private CodeValidationClientRepository codeValidationClientRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JWTUtil jWTUtil;

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
        Integer attempts = 0;
        final Optional<Client> client = repository.findByEmail(email);
        if (!client.isPresent()) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        final LocalDateTime dateExpiration = LocalDateTime.now()
            .plusMinutes(MINUTES_CODE_EXPIRATION);
        String code = null;

        final CodeValidationClient codeValidationExisting = searchValidCode(email);
        if (codeValidationExisting != null) {
            code = codeValidationExisting.getCode();
            attempts = codeValidationExisting.getAttempts();
        } else {
            code = CodeValidation.newPassword(SIZE_CODE_VALIDATION);
        }

        final CodeValidationClient codeValidation =
                new CodeValidationClient(client.get(), code, dateExpiration, attempts);

        codeValidationClientRepository.save(codeValidation);

        emailService.sendNewPasswordEmail(client.get(), code);

    }

    public CodeValidationClient searchValidCode(final String email) {
        List<CodeValidationClient> codes = codeValidationClientRepository.findByClientEmail(email);
        if (codes.isEmpty()) {
            return null;
        }
        codeValidationClientRepository.deleteAll(codes);
        codes = codes.stream()
            .filter(code -> code.getAttempts() < SIZE_MAX_ATTEMPTS)
            .filter(code -> LocalDateTime.now()
                .isBefore(code.getDateExpiration()))
            .collect(Collectors.toList());
        if (codes.isEmpty()) {
            return null;
        }
        return codes.get(0);
    }

    public ResponseClientDTO forgotValidate(final String email, final String codeVerification,
            final HttpServletResponse response) {
        final Optional<CodeValidationClient> code =
                codeValidationClientRepository.findByClientEmailAndCode(email, codeVerification);
        if (!code.isPresent()) {
            final List<CodeValidationClient> codes =
                    codeValidationClientRepository.findByClientEmail(email);
            if (!codes.isEmpty()) {
                for (final CodeValidationClient newCode : codes) {
                    newCode.addAttempts();
                }
                codeValidationClientRepository.saveAll(codes);
            }
            throw new ObjectNotFoundException("O código informado é inválido");
        }
        codeValidationClientRepository.delete(code.get());
        if (code.get()
            .getAttempts() >= SIZE_MAX_ATTEMPTS || code.get()
                .getDateExpiration()
                .isBefore(LocalDateTime.now())) {
            throw new ObjectNotFoundException("Código de validação expirado.");
        }
        final String token = jWTUtil.generateToken(code.get()
            .getClient()
            .getEmail());
        response.addHeader("Authorization", "Bearer " + token);

        return new ResponseClientDTO(code.get()
            .getClient());
    }

    public ResponseClientDTO refreshToken(final HttpServletResponse response) {
        final UserSS user = UserService.authenticated();

        final Optional<Client> client = repository.findByEmail(user.getUsername());
        if (client.isPresent()) {
            final String token = jWTUtil.generateToken(user.getUsername());
            response.addHeader("Authorization", "Bearer " + token);
            return new ResponseClientDTO(client.get());
        }
        return null;
    }

    public ResponseClientDTO findClient() {
        final UserSS user = UserService.authenticated();
        final Optional<Client> client = repository.findByEmail(user.getUsername());
        if (client.isPresent()) {
            return new ResponseClientDTO(client.get());
        }
        return null;
    }

    public ResponseClientDTO updatePassword(final PasswordDTO password) {
        final UserSS user = UserService.authenticated();
        final Optional<Client> client = repository.findByEmail(user.getUsername());
        if (client.isPresent()) {
            client.get()
                .setPassword(pe.encode(password.getPassword()));
            repository.save(client.get());
            return new ResponseClientDTO(client.get());
        }
        return null;
    }
}
