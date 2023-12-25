package org.polytech.covid.web;


import org.polytech.covid.entity.CustomUserDetails;
import org.polytech.covid.entity.Utilisateur;
import org.polytech.covid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

            // Assuming you have a method like getCenterId() in your UserDetails implementation
            Long adminCenterId = ((CustomUserDetails) userDetails).getCenterId();

             // Now, you can use adminCenterId to set the centerId for the new user
            utilisateur.setCenterId(adminCenterId);

            Utilisateur createdUser = utilisateurService.createUser(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    } else {
        // Handle the case where the authentication details are not as expected
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    }
    @PostMapping(path = "api/superadmin/create")
    public ResponseEntity<Utilisateur> createAdmin(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdAdmin = utilisateurService.createAdmin(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

        @PostMapping(path = "api/admin/create2")
    public ResponseEntity<Utilisateur> hello(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateur);
    }


}