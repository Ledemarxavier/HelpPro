package com.HelpPro.repository;

import com.HelpPro.model.Consulta;
import com.HelpPro.enums.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByClienteId(Long clienteId);
    List<Consulta> findByProfissionalId(Long profissionalId);
    List<Consulta> findByStatus(StatusConsulta status);
    List<Consulta> findByClienteIdAndStatus(Long clienteId, StatusConsulta status);
    List<Consulta> findByProfissionalIdAndStatus(Long profissionalId, StatusConsulta status);
}