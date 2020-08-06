package com.julio.amamenteAVida.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.julio.amamenteAVida.external.enums.Profile;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS() {}

    public UserSS(final Integer id, final String email, final String password,
            final Set<Profile> profiles) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profiles.stream()
            .map(x -> new SimpleGrantedAuthority(x.getDescricao()))
            .collect(Collectors.toList());
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(final Profile perfil) {
        return this.getAuthorities()
            .contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }

}
