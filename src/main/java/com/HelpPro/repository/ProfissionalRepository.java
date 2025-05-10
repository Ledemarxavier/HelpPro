package com.HelpPro.repository;

import com.HelpPro.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Profissional findByEmail(String email);
}