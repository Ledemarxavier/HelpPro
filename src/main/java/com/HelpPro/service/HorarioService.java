package com.HelpPro.service;

import com.HelpPro.model.Horario;
import com.HelpPro.model.Profissional;
import com.HelpPro.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Transactional
    public Horario criarHorario(Profissional profissional, String diaSemana,
                               LocalTime horarioInicio, LocalTime horarioFim) {
        Horario horario = new Horario();
        horario.setProfissional(profissional);
        horario.setDiaSemana(diaSemana);
        horario.setHorarioInicio(horarioInicio);
        horario.setHorarioFim(horarioFim);
        horario.setDisponivel(true);
        horario.setDataCriacao(new Date());
        horario.setDataAtualizacao(new Date());
        return horarioRepository.save(horario);
    }

    public List<Horario> getHorariosDisponiveis(Profissional profissional, String diaSemana) {
        return horarioRepository.findByProfissionalIdAndDiaSemana(profissional.getId(), diaSemana)
                .stream()
                .filter(h -> h.isHorarioDisponivel(new Date()))
                .toList();
    }

    public List<Horario> getHorariosDisponiveisPorPeriodo(Profissional profissional,
                                                         String diaSemana,
                                                         LocalTime horarioInicio,
                                                         LocalTime horarioFim) {
        List<Horario> horarios = getHorariosDisponiveis(profissional, diaSemana);
        return horarios.stream()
                .filter(h -> h.getHorarioInicio().isAfter(horarioInicio) &&
                        h.getHorarioFim().isBefore(horarioFim))
                .toList();
    }

    @Transactional
    public void marcarHorarioComoIndisponivel(Long horarioId) {
        Horario horario = horarioRepository.findById(horarioId)
                .orElseThrow(() -> new RuntimeException("Horário não encontrado"));
        horario.setDisponivel(false);
        horario.setDataAtualizacao(new Date());
        horarioRepository.save(horario);
    }

    @Transactional
    public void marcarHorarioComoDisponivel(Long horarioId) {
        Horario horario = horarioRepository.findById(horarioId)
                .orElseThrow(() -> new RuntimeException("Horário não encontrado"));
        horario.setDisponivel(true);
        horario.setDataAtualizacao(new Date());
        horarioRepository.save(horario);
    }
}
