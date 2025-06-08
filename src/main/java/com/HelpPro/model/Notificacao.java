package com.HelpPro.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notificacoes")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false, length = 500)
    private String mensagem;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private boolean lida;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
