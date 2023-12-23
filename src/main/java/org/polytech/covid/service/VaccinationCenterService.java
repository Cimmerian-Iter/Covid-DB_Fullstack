package org.polytech.covid.service;

import java.util.List;

import org.polytech.covid.domain.VaccinationCenter;
import org.polytech.covid.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VaccinationCenterService {

    @Autowired
    private VaccinationCenterRepository centerRepository;

    public List<VaccinationCenter> findAllByCity(String CityName){
        return centerRepository.findAllByCity(CityName);
    }

    public List<VaccinationCenter> findAllByName(String CenterName){
        return centerRepository.findAllByName(CenterName);
    }

        public List<VaccinationCenter> findAllById(int CenterId){
        return centerRepository.findAllById(CenterId);
    }
    public VaccinationCenter addCenter(VaccinationCenter center){
        return centerRepository.save(center);
    }
    public void deleteByName(String name) {
    }
    public boolean existsByName(String name) {
        return centerRepository.existsByName(name);
    }
    public void deleteById(int id) {
        // Implement the logic to delete a center by ID
    }
}
