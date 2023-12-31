package org.polytech.covid.web;

import java.util.List;

import org.polytech.covid.domain.RendezVous;
import org.polytech.covid.entity.CustomUserDetails;
import org.polytech.covid.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RendezVousController {

    @Autowired
    private RendezVousService rendezvousService;

    @PostMapping("/api/public/rendezvous/create")
    public ResponseEntity<String> createRendezvous(@RequestBody RendezVous rendezvous) {
        rendezvousService.createRendezvous(rendezvous);

        String jsonResponse = "{\"message\":\"Rendezvous created successfully!\"}";
        return ResponseEntity.ok(jsonResponse);
    }

    @DeleteMapping("/api/admin/rendezvous/delete/{id}")
    public ResponseEntity<Void> deleteRendezvous(@PathVariable int id) {
        rendezvousService.deleteRendezvous(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/api/user/rendezvous/vaccinate/{id}")
    public ResponseEntity<RendezVous> markAsVaccinated(@PathVariable int id) {
        RendezVous vaccinatedRendezvous = rendezvousService.markAsVaccinated(id);
        if (vaccinatedRendezvous != null) {
            return new ResponseEntity<>(vaccinatedRendezvous, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/user/rendezvous/byName/{name}")
    public ResponseEntity<List<RendezVous>> getAllRendezvousByName(@PathVariable String name) {
        List<RendezVous> rendezvousList = rendezvousService.getAllRendezvousByName(name);
        return new ResponseEntity<>(rendezvousList, HttpStatus.OK);
    }

    @GetMapping("/api/admin/rendezvous/byCenterId")
    public ResponseEntity<List<RendezVous>> getAllRendezvousByCenterId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            Long adminCenterIdLong = ((CustomUserDetails) userDetails).getCenterId();

            // Conversion parce que
            int adminCenterId = adminCenterIdLong.intValue();

            List<RendezVous> rendezvousList = rendezvousService.getAllRendezvousByCenterId(adminCenterId);
            return new ResponseEntity<>(rendezvousList, HttpStatus.OK);
    }   else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

}
