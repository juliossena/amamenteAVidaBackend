package com.julio.amamenteAVida.external.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.julio.amamenteAVida.external.dto.ClientDTO;
import com.julio.amamenteAVida.external.enums.Profile;

import lombok.Data;

@Entity
@Data
public class Client implements Serializable {
    private static final long serialVersionUID = -4123563832794601578L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String profession;
    private Integer donationFrequency;

    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "milk_bank_id")
    private MilkBank milkBank;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private final Set<Integer> profiles = new HashSet<>();

    public Client() {
        addProfile(Profile.CLIENT);
    }

    public Client(final Integer id, final String name, final String email, final String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        addProfile(Profile.CLIENT);
    }

    public Client(final ClientDTO client) {
        name = client.getName();
        email = client.getEmail();
        profession = client.getProfission();
        password = client.getPassword();
        addProfile(Profile.CLIENT);
        donationFrequency = client.getDonationFrequency();
    }

    public void addProfile(final Profile perfil) {
        profiles.add(perfil.getCod());
    }

    public Set<Profile> getProfiles() {
        return profiles.stream()
            .map(x -> Profile.toEnum(x))
            .collect(Collectors.toSet());
    }


}
