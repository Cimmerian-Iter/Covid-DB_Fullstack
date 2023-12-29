package org.polytech.covid.web;


import org.polytech.covid.entity.CustomUserDetails;
import org.polytech.covid.entity.Utilisateur;
import org.polytech.covid.service.Role;
import org.polytech.covid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
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
    String password = credentials.get("password");{

        try {
            Role role = utilisateurService.getUserRoleByLogin(login, password);

            return ResponseEntity.ok("Le rôle de l'utilisateur " + login + " est : " + role.toString());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }
    }

}
}