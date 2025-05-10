package com.HelpPro.controller;

import com.HelpPro.model.Cliente;
import com.HelpPro.model.Profissional;
import com.HelpPro.repository.ClienteRepository;
import com.HelpPro.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @PostMapping("/logar")
    public String logar(@RequestParam String email, @RequestParam String senha) {
        // Login hardcoded para admin
        if (email.equals("admin@helppro.com") && senha.equals("123456")) {
            return "redirect:/dashboard";
        }

        // Verifica login do Cliente
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            return "redirect:/dashboard";
        }

        // Verifica login do Profissional
        Profissional profissional = profissionalRepository.findByEmail(email);
        if (profissional != null && profissional.getSenha().equals(senha)) {
            return "redirect:/dashboard";
        }

        return "redirect:/login?erro=true";
    }

    @GetMapping("/dashboard")
    @ResponseBody
    public String dashboard() {
        return "Bem-vindo ao sistema HelpPro!";
    }
}
