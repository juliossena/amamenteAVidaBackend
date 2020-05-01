package com.julio.amamenteAVida.external.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class CodeValidationClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private String code;
    private LocalDateTime dateExpiration;

    public CodeValidationClient(final Client client, final String code,
            final LocalDateTime dateExpiration) {
        this.client = client;
        this.code = code;
        this.dateExpiration = dateExpiration;
    }

}
