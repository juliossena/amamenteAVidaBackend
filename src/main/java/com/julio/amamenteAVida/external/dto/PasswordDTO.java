package com.julio.amamenteAVida.external.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordDTO implements Serializable {
    private static final long serialVersionUID = 6027080043686168113L;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String password;


}
