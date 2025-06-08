package com.HelpPro.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Profissional extends Usuario {
     @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    @Column(length = 20, nullable = false)
    private String telefone;
     @NotBlank(message = "Especialidade é obrigatória")
    @Column(nullable = false)
    private String especialidade;

    @NotEmpty(message = "Disponibilidade não pode estar vazia")
    @ElementCollection
    private List<String> disponibilidade;
}