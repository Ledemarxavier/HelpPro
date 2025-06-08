package com.HelpPro.repository;

import com.HelpPro.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByProfissionalIdAndDiaSemana(Long profissionalId, String diaSemana);
    List<Horario> findByProfissionalIdAndDisponivel(Long profissionalId, boolean disponivel);
}
