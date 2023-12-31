package org.polytech.covid.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.polytech.covid.entity.Utilisateur;
import org.polytech.covid.repository.UtilisateurRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // On injecte le passwordEncoder dans le service sinon ça bug
        utilisateurService = new UtilisateurService(utilisateurRepository, passwordEncoder);
    }

/* 
    @Test
    void testLoadUserByUsernameSuccess() {
        String login = "user";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setLogin(login);
        utilisateur.setPassword(passwordEncoder.encode("password"));
        utilisateur.setRole(Role.SUPERADMIN);
        utilisateur.setCenterId(0L);
    
        when(utilisateurRepository.findByLogin(login)).thenReturn(Optional.of(utilisateur));
    
        UserDetails userDetails = utilisateurService.loadUserByUsername(login);
    
        assertNotNull(userDetails);

        assertEquals(login, userDetails.getUsername());
    }
    */

    @Test
    void testLoadUserByUsernameUserNotFound() {
        String login = "nonexistent_user";

        when(utilisateurRepository.findByLogin(login)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> utilisateurService.loadUserByUsername(login));
    }

    @Test
    void testGetUserRoleByLoginSuccess() {
        String login = "user";
        String password = "password";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setLogin(login);
        utilisateur.setPassword(passwordEncoder.encode(password));
        utilisateur.setRole(Role.USER);
        utilisateur.setCenterId(1L);

        when(utilisateurRepository.findByLogin(login)).thenReturn(Optional.of(utilisateur));
        when(passwordEncoder.matches(password, utilisateur.getPassword())).thenReturn(true);

        assertEquals(Role.USER, utilisateurService.getUserRoleByLogin(login, password));
    }

    @Test
    void testGetUserRoleByLoginUserNotFound() {
        String login = "nonexistent_user";
        String password = "password";

        when(utilisateurRepository.findByLogin(login)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> utilisateurService.getUserRoleByLogin(login, password));
    }

    @Test
    void testGetUserRoleByLoginIncorrectPassword() {
        String login = "user";
        String password = "incorrect_password";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setLogin(login);
        utilisateur.setPassword(passwordEncoder.encode("correct_password"));
        utilisateur.setRole(Role.USER);
        utilisateur.setCenterId(1L);

        when(utilisateurRepository.findByLogin(login)).thenReturn(Optional.of(utilisateur));
        when(passwordEncoder.matches(password, utilisateur.getPassword())).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> utilisateurService.getUserRoleByLogin(login, password));
    }

}
