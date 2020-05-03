package com.julio.amamenteAVida.external.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.amamenteAVida.external.entity.Dono;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Integer> {


}
