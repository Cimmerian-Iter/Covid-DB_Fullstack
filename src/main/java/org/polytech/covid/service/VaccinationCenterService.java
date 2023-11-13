package org.polytech.covid.service;

import org.polytech.covid.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.polytech.covid.domain.VaccinationCenter;


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
    
}
