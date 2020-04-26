package com.julio.amamenteAVida.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.julio.amamenteAVida.external.entity.Client;
import com.julio.amamenteAVida.external.repository.ClientRepository;
import com.julio.amamenteAVida.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository repo;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final Optional<Client> cli = repo.findByEmail(email);
        if (!cli.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(cli.get().getId(),
                cli.get().getEmail(),
                cli.get().getPassword(),
                cli.get().getProfiles());
    }

}
