package com.julio.amamenteAVida.external.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.julio.amamenteAVida.external.dto.DonoDTO;
import com.julio.amamenteAVida.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Long donationVolume;
    private LocalDate collectionDate;
    private String contact;

    public Dono(final Client client, final DonoDTO dono) {
        this.client = client;
        this.donationVolume = dono.getDonationVolume();
        this.collectionDate =
                DateUtils.convertStringUniversalFormatToDate(dono.getCollectionDate());
        this.contact = dono.getContact();
    }

}
