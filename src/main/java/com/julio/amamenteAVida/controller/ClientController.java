package com.julio.amamenteAVida.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julio.amamenteAVida.external.dto.ClientDTO;
import com.julio.amamenteAVida.external.dto.EmailDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseBaseDTO;
import com.julio.amamenteAVida.service.ClientService;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    public ResponseEntity<ResponseBaseDTO> createNewClient(@RequestBody final ClientDTO client) {
        return ResponseEntity.ok(service.createNewClient(client));
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody final EmailDTO objDTO) {
        service.sendCodeNewPassword(objDTO.getEmail());
        return ResponseEntity.noContent()
            .build();
    }



}
