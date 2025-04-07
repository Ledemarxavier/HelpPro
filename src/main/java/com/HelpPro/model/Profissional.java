package com.HelpPro.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Profissional extends Usuario {

    @Column(length = 20, nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String especialidade;

    @ElementCollection
    private List<String> disponibilidade;
}