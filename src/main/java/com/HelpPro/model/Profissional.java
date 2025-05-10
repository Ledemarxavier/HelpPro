package com.HelpPro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(length = 20, nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String especialidade;

    @ElementCollection
    private List<String> disponibilidade;
}