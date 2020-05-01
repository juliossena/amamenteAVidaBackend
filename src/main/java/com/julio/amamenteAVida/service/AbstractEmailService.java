package com.julio.amamenteAVida.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.julio.amamenteAVida.external.entity.Client;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendNewPasswordEmail(final Client client, final String codeValidation) {
        final SimpleMailMessage sm = prepareNewPassordEmail(client, codeValidation);
        sendEmail(sm);
    }

    private SimpleMailMessage prepareNewPassordEmail(final Client cliente,
            final String codeValidation) {
        final SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(cliente.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Código de validação: " + codeValidation);
        return sm;
    }
}
