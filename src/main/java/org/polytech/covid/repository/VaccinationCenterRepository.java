package org.polytech.covid.repository;

import java.util.List;

import org.polytech.covid.domain.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationCenterRepository 
    extends JpaRepository<VaccinationCenter, Integer> {
        public List<VaccinationCenter> findAllByCity(String city);
        public List<VaccinationCenter> findAllByName(String name);
        public List<VaccinationCenter> findAllById(int id);
        public VaccinationCenter save(VaccinationCenter center);
        boolean existsByName(String name);
        void deleteByName(String name);
}


