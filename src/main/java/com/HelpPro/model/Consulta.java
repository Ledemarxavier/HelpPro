package com.HelpPro.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.HelpPro.enums.StatusConsulta;

@Data
@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 500)
    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;
}