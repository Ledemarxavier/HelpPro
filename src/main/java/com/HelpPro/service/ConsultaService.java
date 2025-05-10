package com.HelpPro.service;

import com.HelpPro.exception.ResourceNotFoundException;
import com.HelpPro.model.Consulta;
import com.HelpPro.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta getConsultaById(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
    }

    public Consulta saveConsulta(Consulta consulta) {
        boolean ocupado = consultaRepository.existsByProfissionalAndData(
                consulta.getProfissional(), consulta.getData()
        );

        if (ocupado) {
            throw new IllegalArgumentException("O profissional já possui uma consulta nesse horário.");
        }

        return consultaRepository.save(consulta);
    }

    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    public Consulta cancelarConsulta(Long id) {
        Consulta consulta = getConsultaById(id);

        long horasRestantes = Duration.between(
                LocalDateTime.now(),
                consulta.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        ).toHours();

        if (horasRestantes < 12) {
            throw new IllegalStateException("Cancelamento só é permitido com pelo menos 12 horas de antecedência.");
        }

        consulta.setStatus(Consulta.StatusConsulta.CANCELADA);
        return consultaRepository.save(consulta);
    }

    public Consulta remarcarConsulta(Long id, Date novaData) {
        Consulta consulta = getConsultaById(id);

        boolean ocupado = consultaRepository.existsByProfissionalAndData(
                consulta.getProfissional(), novaData
        );

        if (ocupado) {
            throw new IllegalArgumentException("O profissional já possui uma consulta nesse novo horário.");
        }

        consulta.setData(novaData);
        consulta.setStatus(Consulta.StatusConsulta.REMARCADA);
        return consultaRepository.save(consulta);
    }
}
