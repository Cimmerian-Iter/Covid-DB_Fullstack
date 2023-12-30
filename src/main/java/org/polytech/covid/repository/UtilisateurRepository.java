package org.polytech.covid.repository;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.entity.Utilisateur;
import org.polytech.covid.service.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByLogin(final String email);
    Optional<Utilisateur> findByLoginAndCenterId(String login, int centerId);
    List<Utilisateur> findByRoleIn(List<Role> roles);
    List<Utilisateur> findByRoleAndCenterId(Role role, long centerId);
}
