package com.HelpPro.controller;

import com.HelpPro.model.Notificacao;
import com.HelpPro.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public ResponseEntity<List<Notificacao>> getNotificacoesPorUsuario(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(notificacaoService.getNotificacoesPorUsuario(usuarioId));
    }

    @GetMapping("/nao-lidas")
    public ResponseEntity<List<Notificacao>> getNotificacoesNaoLidas(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(notificacaoService.getNotificacoesNaoLidas(usuarioId));
    }

    @PutMapping("/marcar-como-lida/{id}")
    public ResponseEntity<Notificacao> marcarComoLida(@PathVariable Long id) {
        return ResponseEntity.ok(notificacaoService.marcarComoLida(id));
    }

    @PutMapping("/marcar-todas-como-lidas")
    public ResponseEntity<Void> marcarTodasComoLidas(@RequestParam Long usuarioId) {
        notificacaoService.marcarTodasComoLidas(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
