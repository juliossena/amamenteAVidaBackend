package com.julio.amamenteAVida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julio.amamenteAVida.external.dto.DonoDTO;
import com.julio.amamenteAVida.external.dto.response.ResponseBaseDTO;
import com.julio.amamenteAVida.service.DonoService;


@RestController
@RequestMapping("/dono")
public class DonoController {

    @Autowired
    private DonoService service;


    @PostMapping
    public ResponseEntity<ResponseBaseDTO> insertDono(@RequestBody final DonoDTO dono) {
        return ResponseEntity.ok(this.service.insertDono(dono));
    }



}
