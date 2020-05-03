package com.julio.amamenteAVida.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.amamenteAVida.external.dto.DonoDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseBaseDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseDonoDTO;
import com.julio.amamenteAVida.external.entity.Client;
import com.julio.amamenteAVida.external.entity.Dono;
import com.julio.amamenteAVida.external.repository.ClientRepository;
import com.julio.amamenteAVida.external.repository.DonoRepository;
import com.julio.amamenteAVida.security.UserSS;

@Service
public class DonoService {

    @Autowired
    private DonoRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    public ResponseBaseDTO insertDono(final DonoDTO donoDTO) {
        final UserSS user = UserService.authenticated();

        final Optional<Client> client = this.clientRepository.findByEmail(user.getUsername());

        final Dono dono = new Dono(client.get(), donoDTO);

        return new ResponseDonoDTO(this.repository.save(dono));
    }

}
