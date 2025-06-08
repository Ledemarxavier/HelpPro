package com.HelpPro.model;

import com.HelpPro.enums.Role;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    private String senha;
    
    private String resetPasswordToken;
    private java.time.LocalDateTime resetPasswordExpires;

    @Enumerated(EnumType.STRING)
    private Role role;
}