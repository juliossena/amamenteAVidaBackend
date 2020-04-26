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
    private String profission;
    private String password;
    private Integer idMilkBank;
    private Integer donationFrequency;

}
