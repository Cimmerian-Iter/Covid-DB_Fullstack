package org.polytech.covid.entity;

import org.polytech.covid.service.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity(name = "UTILISATEUR")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "center_id")
    private Long centerId;
    
    private String login;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;


    
    
    public Long getId() {
        return id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(final String email) {
        this.login = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    
}
