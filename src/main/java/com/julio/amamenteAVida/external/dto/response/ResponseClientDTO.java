package com.julio.amamenteAVida.external.dto.response;

import com.julio.amamenteAVida.external.entity.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseClientDTO extends ResponseBaseDTO {
    private static final long serialVersionUID = 520043632286326113L;

    private String name;
    private String email;
    private String profession;
    private Integer donationFrequency;

    public ResponseClientDTO(final Client client) {
        name = client.getName();
        email = client.getEmail();
        profession = client.getProfession();
        donationFrequency = client.getDonationFrequency();
    }

}
