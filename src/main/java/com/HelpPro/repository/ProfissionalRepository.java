package com.HelpPro.repository;

import com.HelpPro.model.Profissional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; 
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    List<Profissional> findByEspecialidadeIgnoreCase(String especialidade);
    Optional<Profissional> findByEmail(String email);
}