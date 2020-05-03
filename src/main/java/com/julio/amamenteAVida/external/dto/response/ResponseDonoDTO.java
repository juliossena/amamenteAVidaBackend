package com.julio.amamenteAVida.external.dto.response;

import java.time.LocalDate;

import com.julio.amamenteAVida.external.entity.Dono;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseDonoDTO extends ResponseBaseDTO {
    private static final long serialVersionUID = -239217645679628293L;
    
    private Long donationVolume;
    private LocalDate collectionDate;
    private String contact;

    public ResponseDonoDTO(final Dono dono) {
        donationVolume = dono.getDonationVolume();
        collectionDate = dono.getCollectionDate();
        contact = dono.getContact();
    }

}
