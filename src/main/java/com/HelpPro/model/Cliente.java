package com.HelpPro.model;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Cliente extends Usuario {
    
    private String telefone;
}
