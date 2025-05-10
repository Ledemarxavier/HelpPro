package com.HelpPro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HelpPro.exception.ResourceNotFoundException;
import com.HelpPro.model.Profissional;
import com.HelpPro.service.ProfissionalService;
import com.HelpPro.repository.ProfissionalRepository;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired 
    private ProfissionalService profissionalService;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping
    public List<Profissional> getAllProfissionais(){
        return profissionalService.getAllProfissionais();
    }

    @GetMapping("/area/{especialidade}")
    public List<Profissional> buscarPorEspecialidade(@PathVariable String especialidade) {
        return profissionalRepository.findByEspecialidadeContainingIgnoreCase(especialidade);
    }

    @GetMapping("/{id}")
    public Profissional getProfissionalById(@PathVariable Long id){
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));
    }

    @PostMapping
    public Profissional createProfissional(@RequestBody Profissional profissional) {   
        return profissionalService.saveProfissional(profissional);
    }

    @PutMapping("/{id}")
    public Profissional updateProfissional(@PathVariable Long id, @RequestBody Profissional profissional) {
        profissional.setId(id);
        return profissionalService.saveProfissional(profissional);
    }

    @DeleteMapping("/{id}")
    public void deleteProfissional(@PathVariable Long id){
        profissionalService.deleteProfissional(id);
    }
}
