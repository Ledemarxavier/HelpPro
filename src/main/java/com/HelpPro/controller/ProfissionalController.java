package com.HelpPro.controller;

import com.HelpPro.model.Profissional;
import com.HelpPro.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping
    public ResponseEntity<?> getAllProfissionais() {
        return ResponseEntity.ok(profissionalService.getAllProfissionais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfissionalById(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalService.getProfissionalById(id));
    }

    @PostMapping
    public ResponseEntity<?> createProfissional(@RequestBody Profissional profissional) {
        return ResponseEntity.ok(profissionalService.saveProfissional(profissional));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfissional(@PathVariable Long id, @RequestBody Profissional profissional) {
        profissionalService.atualizarProfissional(id, profissional);
        return ResponseEntity.ok(profissional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfissional(@PathVariable Long id) {
        profissionalService.deleteProfissional(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<?> getProfissionaisByEspecialidade(@PathVariable String especialidade) {
        return ResponseEntity.ok(profissionalService.getProfissionaisByEspecialidade(especialidade));
    }
}
