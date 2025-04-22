package com.HelpPro.service;

import com.HelpPro.exception.ResourceNotFoundException;
import com.HelpPro.model.Consulta;
import com.HelpPro.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return consultaRepository.save(consulta);
    }

    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }
}
