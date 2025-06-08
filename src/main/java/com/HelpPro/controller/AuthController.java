package com.HelpPro.controller;

import com.HelpPro.model.Cliente;
import com.HelpPro.model.Profissional;
import com.HelpPro.model.LoginRequest;
import com.HelpPro.enums.Role;
import com.HelpPro.security.JwtUtil;
import com.HelpPro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getSenha()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .orElse("CLIENTE");

            String token = jwtUtil.generateTokenWithRole(authentication.getName(), role);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "email", authentication.getName(),
                    "role", role
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciais inválidas"));
        }
    }

    @PostMapping("/register/cliente")
    public ResponseEntity<?> registerCliente(@RequestBody Cliente cliente) {
        // Validações básicas
        String validationError = validarUsuario(cliente.getEmail(), cliente.getSenha(), cliente.getNome());
        if (validationError != null) {
            return ResponseEntity.badRequest().body(Map.of("error", validationError));
        }

        if (usuarioService.existsByEmail(cliente.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email já cadastrado"));
        }

        cliente.setRole(Role.CLIENTE);
        usuarioService.save(cliente);
        return ResponseEntity.ok(Map.of("message", "Cliente registrado com sucesso"));
    }

    @PostMapping("/register/profissional")
    public ResponseEntity<?> registerProfissional(@RequestBody Profissional profissional) {
        // Validações básicas
        String validationError = validarUsuario(profissional.getEmail(), profissional.getSenha(), profissional.getNome());
        if (validationError != null) {
            return ResponseEntity.badRequest().body(Map.of("error", validationError));
        }

        if (usuarioService.existsByEmail(profissional.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email já cadastrado"));
        }

        profissional.setRole(Role.PROFISSIONAL);
        usuarioService.save(profissional);
        return ResponseEntity.ok(Map.of("message", "Profissional registrado com sucesso"));
    }

    // Método auxiliar para validar email, senha e nome
    private String validarUsuario(String email, String senha, String nome) {
        if (email == null || email.isBlank()) {
            return "Email é obrigatório";
        }
        if (!email.contains("@")) {
            return "Email inválido";
        }
        if (senha == null || senha.length() < 6) {
            return "Senha deve ter no mínimo 6 caracteres";
        }
        if (nome == null || nome.isBlank()) {
            return "Nome é obrigatório";
        }
        return null; // Sem erros
    }
}
