package com.julio.amamenteAVida.external.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseBaseDTO implements Serializable {
    private static final long serialVersionUID = 1888222116851183869L;

    @JsonInclude(Include.NON_NULL)
    private final String message;

    public ResponseBaseDTO() {
        message = null;
    }

}
