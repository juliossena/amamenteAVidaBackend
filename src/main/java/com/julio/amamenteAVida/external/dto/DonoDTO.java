package com.julio.amamenteAVida.external.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonoDTO implements Serializable {
    private static final long serialVersionUID = 3503397377222825363L;

    private Long donationVolume;
    private String collectionDate;
    private String contact;


}
