package com.julio.amamenteAVida.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    
    private String name;
    private String email;
    private String password;
    private String profession;
    private String birthDate;
    private String cpf;
    private Boolean isDonor;

}
