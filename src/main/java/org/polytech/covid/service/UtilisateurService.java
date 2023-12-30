package org.polytech.covid.service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.entity.CustomUserDetails;
import org.polytech.covid.entity.Utilisateur;
import org.polytech.covid.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
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

    public Role getUserRoleByLogin(String login, String password) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByLogin(login);

        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();

            // Check if the password is correct
            if (passwordEncoder.matches(password, utilisateur.getPassword())) {
                return utilisateur.getRole();
            } else {
                throw new BadCredentialsException("Mot de passe incorrect");
            }
        } else {
            throw new UsernameNotFoundException("L'utilisateur " + login + " n'existe pas");
        }
    }
    public Utilisateur updateUsernameOrPassword(long userId, String newLogin, String newPassword) {
        Utilisateur user = utilisateurRepository.findById(userId).orElse(null);

        if (user != null) {
            // Update username if provided
            if (newLogin != null && !newLogin.isEmpty()) {
                user.setLogin(newLogin);
            }

            // Update password if provided
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }

            return utilisateurRepository.save(user);
        }

        return null;
    }
    public Utilisateur updateAuthorizedUser(long userId, String newLogin, String newPassword, long centerId) {
        Utilisateur user = utilisateurRepository.findById(userId).orElse(null);

        if (user != null && user.getRole() == Role.USER && user.getCenterId() == centerId) {
            if (newLogin != null && !newLogin.isEmpty()) {
                user.setLogin(newLogin);
            }
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }

            return utilisateurRepository.save(user);
        }

        return null;
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
            public Utilisateur createSuperAdmin(Utilisateur utilisateur) {
        utilisateur.setLogin(utilisateur.getLogin());
        utilisateur.setCenterId(utilisateur.getCenterId());
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.setRole(Role.SUPERADMIN);
        return utilisateurRepository.save(utilisateur);
    }
    public List<Utilisateur> getAllAdminUsers() {
        return utilisateurRepository.findByRoleIn(List.of(Role.ADMIN));
    }
    public void deleteUserById(long userId) {
        utilisateurRepository.deleteById(userId);
    }
    public List<Utilisateur> getAllUsersByCenterId(long centerId) {
        return utilisateurRepository.findByRoleAndCenterId(Role.USER, centerId);
    }
}
