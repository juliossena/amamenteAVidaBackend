package com.julio.amamenteAVida.external.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = -596483918836424367L;

    private String email;


}
