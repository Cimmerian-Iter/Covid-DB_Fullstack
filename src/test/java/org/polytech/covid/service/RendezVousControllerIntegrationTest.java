package org.polytech.covid.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.polytech.covid.domain.RendezVous;
import org.polytech.covid.web.RendezVousController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RendezVousController.class)
@AutoConfigureMockMvc
public class RendezVousControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RendezVousService rendezVousService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateRendezvous() throws Exception {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setName("John Doe");
        rendezVous.setEmail("john.doe@example.com");

        when(rendezVousService.createRendezvous(any(RendezVous.class)))
    .thenAnswer(invocation -> {
        RendezVous createdRendezVous = invocation.getArgument(0);
        // Set any expected values for the created rendezvous
        createdRendezVous.setId(1); // Assuming there is an setId method

        return createdRendezVous;
    });
    }
    // Add more integration tests for other controller methods as needed
}
