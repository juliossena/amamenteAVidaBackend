package com.julio.amamenteAVida.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.julio.amamenteAVida.service.EmailService;
import com.julio.amamenteAVida.service.SmtpEmailService;

@Configuration
public class EmailConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
