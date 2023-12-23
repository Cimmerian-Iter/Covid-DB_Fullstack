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
        // You can add validation or business logic here before saving
        return centerRepository.save(center);
    }
}
