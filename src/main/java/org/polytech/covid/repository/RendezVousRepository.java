package org.polytech.covid.repository;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.domain.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Integer> {

    Optional<RendezVous> findById(int id);

    List<RendezVous> findByName(String name);

    void deleteById(int id);

    List<RendezVous> findByCenterId(int centerId);
    

    Optional<RendezVous> findByEmail(String email);
}