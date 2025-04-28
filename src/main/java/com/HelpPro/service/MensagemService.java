package com.HelpPro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HelpPro.model.Mensagem;
import com.HelpPro.repository.MensagemRepository;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Mensagem> getAllMensagens() {
        return mensagemRepository.findAll();
    }

    public Optional<Mensagem> getMensagemById(Long id) {
        return mensagemRepository.findById(id);
    }

    public Mensagem saveMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    public void deleteMensagem(Long id) {
        mensagemRepository.deleteById(id);
    }
}