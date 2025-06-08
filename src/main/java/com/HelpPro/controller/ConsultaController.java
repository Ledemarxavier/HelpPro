package com.HelpPro.controller;

import com.HelpPro.model.Consulta;
import com.HelpPro.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<?> agendarConsulta(@RequestParam Long clienteId,
                                             @RequestParam Long profissionalId,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHora,
                                             @RequestParam String motivo) {
        return ResponseEntity.ok(consultaService.agendarConsulta(clienteId, profissionalId, dataHora, motivo));
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmarConsulta(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.confirmarConsulta(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarConsulta(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.cancelarConsulta(id));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Consulta>> getConsultasPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.getConsultasPorCliente(id));
    }

    @GetMapping("/profissional/{id}")
    public ResponseEntity<List<Consulta>> getConsultasPorProfissional(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.getConsultasPorProfissional(id));
    }

    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    

    @PutMapping("/{id}/remarcar")
    public ResponseEntity<?> remarcarConsulta(@PathVariable Long id,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime novaDataHora) {
        return ResponseEntity.ok(consultaService.remarcarConsulta(id, novaDataHora));
    }

    @PutMapping("/{id}/atualizar-motivo")
    public ResponseEntity<?> atualizarMotivo(@PathVariable Long id,
                                             @RequestParam String novoMotivo) {
        return ResponseEntity.ok(consultaService.atualizarMotivo(id, novoMotivo));
    }

    @GetMapping("/pendentes/profissional/{profissionalId}")
    public ResponseEntity<List<Consulta>> listarPendentesPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(consultaService.getPendentesPorProfissional(profissionalId));
    }

    @GetMapping("/pendentes/cliente/{clienteId}")
    public ResponseEntity<List<Consulta>> listarPendentesPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(consultaService.getPendentesPorCliente(clienteId));
    }
} 
