package com.HelpPro.controller;

import com.HelpPro.model.Consulta;
import com.HelpPro.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> getAllConsultas() {
        return consultaService.getAllConsultas();
    }

    @GetMapping("/{id}")
    public Consulta getConsultaById(@PathVariable Long id) {
        return consultaService.getConsultaById(id);
    }

    @PostMapping
    public Consulta createConsulta(@RequestBody Consulta consulta) {
        return consultaService.saveConsulta(consulta);
    }

    @PutMapping("/{id}")
    public Consulta updateConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        consulta.setId(id);
        return consultaService.saveConsulta(consulta);
    }

    @DeleteMapping("/{id}")
    public void deleteConsulta(@PathVariable Long id) {
        consultaService.deleteConsulta(id);
    }
}