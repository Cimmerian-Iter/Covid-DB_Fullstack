package org.polytech.covid.web;

import org.polytech.covid.domain.RendezVous;
import org.polytech.covid.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezvousService;

    @PostMapping("/create")
    public ResponseEntity<String> createRendezvous(@RequestBody RendezVous rendezvous) {
        rendezvousService.createRendezvous(rendezvous);

        return ResponseEntity.ok("Rendezvous created successfully!");
    }

}
