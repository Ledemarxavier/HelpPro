package com.HelpPro.service;

import com.HelpPro.model.Usuario;
import com.HelpPro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.reset-password.expiration-hours:24}")
    private int resetPasswordExpirationHours;

    @Transactional
    public void requestPasswordReset(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));

        String token = UUID.randomUUID().toString();
        LocalDateTime expires = LocalDateTime.now().plusHours(resetPasswordExpirationHours);

        usuario.setResetPasswordToken(token);
        usuario.setResetPasswordExpires(expires);
        usuarioRepository.save(usuario);

        sendPasswordResetEmail(usuario, token);
    }

    private void sendPasswordResetEmail(Usuario usuario, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(usuario.getEmail());
        message.setSubject("Recuperação de Senha - HelpPro");
        message.setText("Olá!\n\n" +
                "Você solicitou a recuperação de senha.\n" +
                "Clique no link abaixo para definir uma nova senha:\n\n" +
                "http://localhost:8080/api/auth/reset-password/" + token + "\n\n" +
                "Este link expira em " + resetPasswordExpirationHours + " horas.");

        mailSender.send(message);
    }

    public Usuario validateResetToken(String token) {
        Usuario usuario = usuarioRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (usuario.getResetPasswordExpires().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        return usuario;
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {
        Usuario usuario = validateResetToken(token);
        usuario.setSenha(newPassword);
        usuario.setResetPasswordToken(null);
        usuario.setResetPasswordExpires(null);
        usuarioRepository.save(usuario);
    }
}
