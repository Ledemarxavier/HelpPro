package com.HelpPro.controller;

import com.HelpPro.model.Cliente;
import com.HelpPro.model.Profissional;
import com.HelpPro.repository.ClienteRepository;
import com.HelpPro.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    // Página de cadastro para Cliente
    @GetMapping("/cadastro-cliente")
    public String cadastroClientePage() {
        return "cadastro-cliente.html";
    }

    // Página de cadastro para Profissional
    @GetMapping("/cadastro-profissional")
    public String cadastroProfissionalPage() {
        return "cadastro-profissional.html";
    }

    // Salvar Cliente
    @PostMapping("/salvar-cliente")
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/login";
    }

    // Salvar Profissional
    @PostMapping("/salvar-profissional")
    public String salvarProfissional(@ModelAttribute Profissional profissional) {
        profissionalRepository.save(profissional);
        return "redirect:/login";
    }
}