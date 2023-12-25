package org.polytech.covid.service;

import java.util.Optional;

import org.polytech.covid.entity.CustomUserDetails;
import org.polytech.covid.entity.Utilisateur;
import org.polytech.covid.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UtilisateurService implements UserDetailsService {
    private static Logger log = LoggerFactory.getLogger(UtilisateurService.class);
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(final UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Pour l'exemple j'enregistre un utilisateur au demarrage
     */
    @PostConstruct
    public void createUserDefault() {
        log.info("Creation du superadmin par défaut");
        // en base 64 : dXNlcjpwYXNzd29yZA==
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setLogin("user");
        utilisateur.setPassword(passwordEncoder.encode("password"));
        utilisateur.setRole(Role.SUPERADMIN);
        utilisateur.setCenterId(0L);
        this.utilisateurRepository.save(utilisateur);
    }

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        log.info("Recuperation de {}", login);

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByLogin(login);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            return new CustomUserDetails(utilisateur);
        } else {
            throw new UsernameNotFoundException("L'utilisateur " + login + " n'existe pas");
        }
    }
    public Utilisateur createUser(Utilisateur utilisateur) {
        utilisateur.setLogin(utilisateur.getLogin());
        utilisateur.setCenterId(utilisateur.getCenterId());
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.setRole(Role.USER);
        return utilisateurRepository.save(utilisateur);
    }
        public Utilisateur createAdmin(Utilisateur utilisateur) {
        utilisateur.setLogin(utilisateur.getLogin());
        utilisateur.setCenterId(utilisateur.getCenterId());
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.setRole(Role.ADMIN);
        return utilisateurRepository.save(utilisateur);
    }
}
