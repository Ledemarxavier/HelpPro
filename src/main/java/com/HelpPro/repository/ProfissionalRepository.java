package com.HelpPro.repository;

import com.HelpPro.model.Profissional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Profissional findByEmail(String email);
    List<Profissional> findByEspecialidadeContainingIgnoreCase(String especialidade);
}