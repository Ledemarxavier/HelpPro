package com.HelpPro.service;

import com.HelpPro.model.Profissional;
import com.HelpPro.model.Usuario;
import com.HelpPro.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Profissional> getAllProfissionais() {
        return profissionalRepository.findAll();
    }

    public Profissional getProfissionalById(Long id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
    }

    public Profissional getProfissionalByEmail(String email) {
        return profissionalRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
    }

    @Transactional
    public Profissional saveProfissional(Profissional profissional) {
        profissionalRepository.findById(profissional.getId()).ifPresent(existing -> {
            throw new IllegalArgumentException("Profissional com este ID já existe.");
        });
        return profissionalRepository.save(profissional);
    }

    @Transactional
    public void deleteProfissional(Long id) {
        profissionalRepository.deleteById(id);
    }

    @Transactional
    public void atualizarProfissional(Long id, Profissional profissional) {
        Profissional profissionalExistente = getProfissionalById(id);
        profissionalExistente.setNome(profissional.getNome());
        profissionalExistente.setEmail(profissional.getEmail());
        profissionalExistente.setSenha(profissional.getSenha());
        profissionalExistente.setTelefone(profissional.getTelefone());
        profissionalExistente.setEspecialidade(profissional.getEspecialidade());
        profissionalExistente.setDisponibilidade(profissional.getDisponibilidade());
        profissionalRepository.save(profissionalExistente);
    }

    @Transactional
    public Profissional tornarProfissional(Profissional profissional, String email) {
        Usuario usuario = usuarioService.findByEmail(email);
        profissional.setId(usuario.getId());
        profissional.setEmail(usuario.getEmail());
        profissional.setNome(usuario.getNome());
        profissional.setSenha(usuario.getSenha());
        profissional.setRole(usuario.getRole());
        return profissionalRepository.save(profissional);
    }

    public List<Profissional> getProfissionaisByEspecialidade(String especialidade) {
        return profissionalRepository.findByEspecialidadeIgnoreCase(especialidade);
    }
}