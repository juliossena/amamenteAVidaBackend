package com.julio.amamenteAVida.external.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.amamenteAVida.external.entity.CodeValidationClient;

@Repository
public interface CodeValidationClientRepository
        extends JpaRepository<CodeValidationClient, Integer> {

    Optional<CodeValidationClient> findByClientEmailAndCode(String email, String code);

    List<CodeValidationClient> findByClientEmail(String email);

}
