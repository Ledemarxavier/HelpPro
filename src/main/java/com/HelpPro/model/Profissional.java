package com.HelpPro.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Profissional extends Usuario {
    
    private String telefone;
    private String especialidade;

    @ElementCollection
    private List<String> disponibilidade;
}