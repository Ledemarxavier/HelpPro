package com.HelpPro.service;

import com.HelpPro.model.Notificacao;
import com.HelpPro.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<Notificacao> getNotificacoesPorUsuario(Long usuarioId) {
        return notificacaoRepository.findByUsuarioId(usuarioId);
    }

    public List<Notificacao> getNotificacoesNaoLidas(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdAndLidaFalse(usuarioId);
    }

    @Transactional
    public Notificacao marcarComoLida(Long notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        notificacao.setLida(true);
        return notificacaoRepository.save(notificacao);
    }

    @Transactional
    public void marcarTodasComoLidas(Long usuarioId) {
        List<Notificacao> notificacoes = notificacaoRepository.findByUsuarioIdAndLidaFalse(usuarioId);
        for (Notificacao notificacao : notificacoes) {
            notificacao.setLida(true);
        }
        notificacaoRepository.saveAll(notificacoes);
    }
}
