package com.julio.amamenteAVida.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julio.amamenteAVida.external.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager,
            final JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest req,
            final HttpServletResponse res) throws AuthenticationException {
        try {
            final CredenciaisDTO creds =
                    new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);

            final UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),
                            new ArrayList<>());

            final Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest req,
            final HttpServletResponse res, final FilterChain chain, final Authentication auth)
            throws IOException, ServletException {

        final String email = ((UserSS) auth.getPrincipal()).getUsername();
        final String token = jwtUtil.generateToken(email);
        res.addHeader("Authorization", "Bearer " + token);
    }

}
