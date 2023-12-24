package org.polytech.covid.web;


import org.polytech.covid.entity.Utilisateur;
import org.polytech.covid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/create")
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUser = utilisateurService.createUser(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}