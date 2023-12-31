package org.polytech.covid.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.polytech.covid.domain.RendezVous;
import org.polytech.covid.repository.RendezVousRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RendezVousServiceTest {

    @Mock
    private RendezVousRepository rendezVousRepository;

    @InjectMocks
    private RendezVousService rendezVousService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRendezvous() {
        // Simulation du repository
        when(rendezVousRepository.findAll()).thenReturn(Collections.emptyList());

        List<RendezVous> allRendezvous = rendezVousService.getAllRendezvous();

        assertNotNull(allRendezvous);
        assertTrue(allRendezvous.isEmpty());

         // On verifie que le repository a été appelé
        verify(rendezVousRepository, times(1)).findAll();
    }

    @Test
    void testGetRendezvousById() {
        int rendezVousId = 1;
        RendezVous mockRendezVous = new RendezVous();
        mockRendezVous.setId(rendezVousId);

        // Simulation du repository
        when(rendezVousRepository.findById(rendezVousId)).thenReturn(Optional.of(mockRendezVous));

        RendezVous retrievedRendezVous = rendezVousService.getRendezvousById(rendezVousId);

        assertNotNull(retrievedRendezVous);
        assertEquals(rendezVousId, retrievedRendezVous.getId());

        // On verifie que le repository a été appelé
        verify(rendezVousRepository, times(1)).findById(rendezVousId);
    }

}
