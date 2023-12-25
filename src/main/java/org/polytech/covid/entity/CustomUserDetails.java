package org.polytech.covid.entity;

import java.util.Collection;
import java.util.List;

import org.polytech.covid.service.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

private final Long centerId;
private static Logger log = LoggerFactory.getLogger(CustomUserDetails.class);

    public CustomUserDetails(Utilisateur utilisateur) {
        super(utilisateur.getLogin(), utilisateur.getPassword(), getAuthorities(utilisateur.getRole()));
        this.centerId = utilisateur.getCenterId();
    }

    public Long getCenterId() {
        return centerId;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}

