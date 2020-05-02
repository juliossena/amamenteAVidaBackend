package com.julio.amamenteAVida.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.julio.amamenteAVida.external.dto.ClientDTO;
import com.julio.amamenteAVida.external.dto.EmailDTO;
import com.julio.amamenteAVida.external.dto.PasswordDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseBaseDTO;
import com.julio.amamenteAVida.service.ClientService;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<ResponseBaseDTO> findClient() {
        return ResponseEntity.ok(service.findClient());
    }

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

    @GetMapping(value = "/forgot")
    public ResponseEntity<ResponseBaseDTO> forgotValidate(
            @RequestParam(value = "email") final String email,
            @RequestParam("codeVerification") final String codeVerification,
            final HttpServletResponse response) {
        return ResponseEntity.ok(service.forgotValidate(email, codeVerification, response));
    }

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<ResponseBaseDTO> refreshToken(final HttpServletResponse response) {
        return ResponseEntity.ok(service.refreshToken(response));
    }

    @PatchMapping(value = "/password")
    public ResponseEntity<ResponseBaseDTO> updatePassword(@RequestBody final PasswordDTO password) {

        return ResponseEntity.ok(service.updatePassword(password));
    }



}
