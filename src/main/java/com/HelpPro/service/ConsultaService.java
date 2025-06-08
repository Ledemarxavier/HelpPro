package com.HelpPro.service;

import com.HelpPro.model.Cliente;
import com.HelpPro.model.Consulta;
import com.HelpPro.model.Notificacao;
import com.HelpPro.model.Profissional;
import com.HelpPro.enums.StatusConsulta;
import com.HelpPro.repository.ConsultaRepository;
import com.HelpPro.repository.NotificacaoRepository;
import com.HelpPro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Consulta agendarConsulta(Long clienteId, Long profissionalId, LocalDateTime dataHora, String motivo) {
        Cliente cliente = usuarioRepository.findById(clienteId)
                .filter(u -> u instanceof Cliente)
                .map(u -> (Cliente) u)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Profissional profissional = usuarioRepository.findById(profissionalId)
                .filter(u -> u instanceof Profissional)
                .map(u -> (Profissional) u)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setCliente(cliente);
        consulta.setProfissional(profissional);
        consulta.setDataHora(dataHora);
        consulta.setMotivo(motivo);
        consulta.setStatus(StatusConsulta.PENDENTE);
        consulta.setDataCriacao(LocalDateTime.now());

        Notificacao notificacao = new Notificacao();
        notificacao.setUsuario(profissional);
        notificacao.setMensagem("Você tem uma nova consulta agendada para " + dataHora);
        notificacao.setDataCriacao(LocalDateTime.now());
        notificacao.setLida(false);
        notificacao.setConsulta(consulta);

        notificacaoRepository.save(notificacao);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta confirmarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consulta.setStatus(StatusConsulta.CONFIRMADA);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta cancelarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consulta.setStatus(StatusConsulta.CANCELADA);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public void deletarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consultaRepository.delete(consulta);
    }

    @Transactional
    public Consulta remarcarConsulta(Long consultaId, LocalDateTime novaDataHora) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consulta.setDataHora(novaDataHora);
        consulta.setStatus(StatusConsulta.PENDENTE);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta atualizarMotivo(Long consultaId, String novoMotivo) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consulta.setMotivo(novoMotivo);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> getConsultasPorCliente(Long clienteId) {
        return consultaRepository.findByClienteId(clienteId);
    }

    public List<Consulta> getConsultasPorProfissional(Long profissionalId) {
        return consultaRepository.findByProfissionalId(profissionalId);
    }

    public List<Consulta> getPendentesPorCliente(Long clienteId) {
        return consultaRepository.findByClienteIdAndStatus(clienteId, StatusConsulta.PENDENTE);
    }

    public List<Consulta> getPendentesPorProfissional(Long profissionalId) {
        return consultaRepository.findByProfissionalIdAndStatus(profissionalId, StatusConsulta.PENDENTE);
    }
}
