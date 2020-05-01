package com.julio.amamenteAVida.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.julio.amamenteAVida.external.entity.Client;

public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Client client, String codeValidation);

    void sendHtmlEmail(MimeMessage msg);

}
