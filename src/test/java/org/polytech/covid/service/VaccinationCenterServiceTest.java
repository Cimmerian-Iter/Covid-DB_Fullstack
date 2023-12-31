package org.polytech.covid.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.polytech.covid.domain.VaccinationCenter;
import org.polytech.covid.repository.VaccinationCenterRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VaccinationCenterServiceTest {

    @InjectMocks
    private VaccinationCenterService vaccinationCenterService;

    @Mock
    private VaccinationCenterRepository centerRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllCenters() {
        VaccinationCenter center1 = new VaccinationCenter();
        center1.setId(1);
        center1.setName("Centre 1");
        center1.setCity("Nancy");
        center1.setAddress("1 rue du centre");

        VaccinationCenter center2 = new VaccinationCenter();
        center2.setId(2);
        center2.setName("Centre 2");
        center2.setCity("Lille");
        center2.setAddress("1 rue du centre");

        List<VaccinationCenter> centerList = new ArrayList<>();
        centerList.add(center1);
        centerList.add(center2);

        when(centerRepository.findAll()).thenReturn(centerList);
        List<VaccinationCenter> returnedCenters = vaccinationCenterService.findAll();

        assertEquals(centerList, returnedCenters);
    }

    @Test
    void findAllCentersByCity() {
        VaccinationCenter center1 = new VaccinationCenter();
        center1.setId(1);
        center1.setName("Centre 1");
        center1.setCity("Nancy");
        center1.setAddress("1 rue du centre");

        when(centerRepository.findAllByCity("Nancy")).thenReturn(List.of(center1));
        List<VaccinationCenter> returnedCenters = vaccinationCenterService.findAllByCity("Nancy");

        assertEquals(List.of(center1), returnedCenters);
    }

    @Test
    void findAllCentersByName() {
        VaccinationCenter center1 = new VaccinationCenter();
        center1.setId(1);
        center1.setName("Centre 1");
        center1.setCity("Nancy");
        center1.setAddress("1 rue du centre");

        when(centerRepository.findAllByName("Centre 1")).thenReturn(List.of(center1));
        List<VaccinationCenter> returnedCenters = vaccinationCenterService.findAllByName("Centre 1");

        assertEquals(List.of(center1), returnedCenters);
    }

}
