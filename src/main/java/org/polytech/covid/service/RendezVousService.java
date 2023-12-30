package org.polytech.covid.service;

import java.util.List;

import org.polytech.covid.domain.RendezVous;
import org.polytech.covid.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RendezVousService {
    private final RendezVousRepository rendezvousRepository;

    @Autowired
    public RendezVousService(RendezVousRepository rendezvousRepository) {
        this.rendezvousRepository = rendezvousRepository;
    }

    public List<RendezVous> getAllRendezvous() {
        return rendezvousRepository.findAll();
    }

    public RendezVous getRendezvousById(int id) {
        return rendezvousRepository.findById(id).orElse(null);
    }

    public RendezVous createRendezvous(RendezVous rendezvous) {

        System.out.println("CenterId: " + rendezvous.getCenterId());
        return rendezvousRepository.save(rendezvous);
    }

    public RendezVous updateRendezvous(int id, RendezVous updatedRendezvous) {

        RendezVous existingRendezvous = rendezvousRepository.findById(id).orElse(null);

        if (existingRendezvous != null) {

            existingRendezvous.setEmail(updatedRendezvous.getEmail());
            existingRendezvous.setPhone(updatedRendezvous.getPhone());
            existingRendezvous.setName(updatedRendezvous.getName());
            existingRendezvous.setSurname(updatedRendezvous.getSurname());
            existingRendezvous.setDate(updatedRendezvous.getDate());

            return rendezvousRepository.save(existingRendezvous);
        }

        return null; 
    }
    public List<RendezVous> getAllRendezvousByName(String name) {
        return rendezvousRepository.findByName(name);
    }

    public List<RendezVous> getAllRendezvousByCenterId(int centerId) {
        return rendezvousRepository.findByCenterId(centerId);
    }

    public void deleteRendezvous(int id) {
        rendezvousRepository.deleteById(id);
    }

    public RendezVous markAsVaccinated(int id) {
        RendezVous rendezvous = rendezvousRepository.findById(id).orElse(null);

        if (rendezvous != null) {
            rendezvous.setVaccination(1);
            return rendezvousRepository.save(rendezvous);
        }

        return null;
    }
    
}

