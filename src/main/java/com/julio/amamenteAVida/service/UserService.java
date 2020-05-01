package com.julio.amamenteAVida.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.julio.amamenteAVida.security.UserSS;

public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        } catch (final Exception e) {
            return null;
        }
    }
}
