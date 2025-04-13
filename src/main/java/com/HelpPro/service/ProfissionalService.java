package com.HelpPro.service;

import com.HelpPro.model.Profissional;
import com.HelpPro.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public List<Profissional> getAllProfissionais() {
        return profissionalRepository.findAll();
    }

    public Profissional saveProfissional(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public void deleteProfissional(Long id) {
        profissionalRepository.deleteById(id);
    }
}
