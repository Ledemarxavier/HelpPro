package com.HelpPro.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "horarios")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @Column(nullable = false)
    private String diaSemana; // "segunda", "terca", etc.

    @Column(nullable = false)
    private LocalTime horarioInicio;

    @Column(nullable = false)
    private LocalTime horarioFim;

    @Column(nullable = false)
    private boolean disponivel;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public boolean isHorarioDisponivel(Date data) {
        // Verifica se o horário está disponível e se não está em um feriado
        return disponivel && !isFeriado(data);
    }

    private boolean isFeriado(Date data) {
        // Implementação de verificação de feriados
        // Aqui você pode implementar a lógica de feriados
        return false;
    }
}
