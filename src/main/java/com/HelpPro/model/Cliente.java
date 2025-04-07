package com.HelpPro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Cliente extends Usuario {
    
     @Column(length = 20, nullable = false)
    private String telefone;
}
