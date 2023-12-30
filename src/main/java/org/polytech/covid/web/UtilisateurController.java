package org.polytech.covid.web;

import java.util.List;

import org.polytech.covid.entity.CustomUserDetails;
import org.polytech.covid.entity.Utilisateur;
import org.polytech.covid.service.Role;
import org.polytech.covid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping(path = "api/admin/create")
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur utilisateur) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            Long adminCenterId = ((CustomUserDetails) userDetails).getCenterId();

            utilisateur.setCenterId(adminCenterId);

            Utilisateur createdUser = utilisateurService.createUser(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    }
    @PostMapping(path = "api/superadmin/create")
    public ResponseEntity<Utilisateur> createAdmin(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdAdmin = utilisateurService.createAdmin(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PostMapping(path = "api/superadmin/supercreate")
    public ResponseEntity<Utilisateur> createSuperAdmin(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdSuperAdmin = utilisateurService.createAdmin(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSuperAdmin);
    }

    @PostMapping("/api/public/getRole")
    public ResponseEntity<String> getUserRole(@RequestBody java.util.Map<String, String> credentials) {
    String login = credentials.get("login");
    String password = credentials.get("password");

    try {
        Role role = utilisateurService.getUserRoleByLogin(login, password);

        String jsonResponse = "{\"role\":\"" + role.toString() + "\"}";
        return ResponseEntity.ok(jsonResponse);
    } catch (UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
    } catch (BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
    }


}
    @DeleteMapping("/api/superadmin/deleteUser/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable long id) {
        utilisateurService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/api/superadmin/getAllAdminUsers")
    public ResponseEntity<List<Utilisateur>> getAllAdminUsers() {
        List<Utilisateur> adminUsers = utilisateurService.getAllAdminUsers();
        return new ResponseEntity<>(adminUsers, HttpStatus.OK);
    }
    @PutMapping("/api/superadmin/updateUser/{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable long id, @RequestBody Utilisateur updatedUser) {
        Utilisateur updated = utilisateurService.updateUsernameOrPassword(id, updatedUser.getLogin(), updatedUser.getPassword());
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/admin/getAllUsersByCenterId")
    public ResponseEntity<List<Utilisateur>> getAllUsersByCenterId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            long centerId = ((CustomUserDetails) userDetails).getCenterId();

            List<Utilisateur> users = utilisateurService.getAllUsersByCenterId(centerId);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping("/api/admin/updateAuthorizedUser/{id}")
    public ResponseEntity<Utilisateur> updateAuthorizedUser(
            @PathVariable long id,
            @RequestBody Utilisateur updatedUser,
            Authentication authentication) {

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            long centerId = ((CustomUserDetails) userDetails).getCenterId();

            Utilisateur updated = utilisateurService.updateAuthorizedUser(
                    id,
                    updatedUser.getLogin(),
                    updatedUser.getPassword(),
                    centerId
            );

            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // User not authorized to update or not found
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Unauthorized request
        }
    }
}