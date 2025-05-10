package com.HelpPro.repository;

import com.HelpPro.model.Consulta;
import com.HelpPro.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByProfissionalAndData(Profissional profissional, Date data);
}
