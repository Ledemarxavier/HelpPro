package com.HelpPro.controller;

import com.HelpPro.model.Horario;
import com.HelpPro.model.Profissional;
import com.HelpPro.service.HorarioService;
import com.HelpPro.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<?> criarHorario(@RequestBody HorarioRequest horarioRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profissional profissional = profissionalService.getProfissionalByEmail(authentication.getName());

        Horario horario = horarioService.criarHorario(
                profissional,
                horarioRequest.getDiaSemana(),
                horarioRequest.getHorarioInicio(),
                horarioRequest.getHorarioFim()
        );

        return ResponseEntity.ok(horario);
    }

    @GetMapping("/disponiveis/{diaSemana}")
    public ResponseEntity<?> getHorariosDisponiveis(@PathVariable String diaSemana) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profissional profissional = profissionalService.getProfissionalByEmail(authentication.getName());

        List<Horario> horarios = horarioService.getHorariosDisponiveis(profissional, diaSemana);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/disponiveis/periodo")
    public ResponseEntity<?> getHorariosDisponiveisPorPeriodo(@RequestParam String diaSemana,
                                                             @RequestParam LocalTime horarioInicio,
                                                             @RequestParam LocalTime horarioFim) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profissional profissional = profissionalService.getProfissionalByEmail(authentication.getName());

        List<Horario> horarios = horarioService.getHorariosDisponiveisPorPeriodo(
                profissional,
                diaSemana,
                horarioInicio,
                horarioFim
        );

        return ResponseEntity.ok(horarios);
    }

    @PutMapping("/{horarioId}/indisponivel")
    public ResponseEntity<?> marcarHorarioComoIndisponivel(@PathVariable Long horarioId) {
        horarioService.marcarHorarioComoIndisponivel(horarioId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{horarioId}/disponivel")
    public ResponseEntity<?> marcarHorarioComoDisponivel(@PathVariable Long horarioId) {
        horarioService.marcarHorarioComoDisponivel(horarioId);
        return ResponseEntity.ok().build();
    }

    public static class HorarioRequest {
        private String diaSemana;
        private LocalTime horarioInicio;
        private LocalTime horarioFim;

        public String getDiaSemana() {
            return diaSemana;
        }

        public void setDiaSemana(String diaSemana) {
            this.diaSemana = diaSemana;
        }

        public LocalTime getHorarioInicio() {
            return horarioInicio;
        }

        public void setHorarioInicio(LocalTime horarioInicio) {
            this.horarioInicio = horarioInicio;
        }

        public LocalTime getHorarioFim() {
            return horarioFim;
        }

        public void setHorarioFim(LocalTime horarioFim) {
            this.horarioFim = horarioFim;
        }
    }
}
