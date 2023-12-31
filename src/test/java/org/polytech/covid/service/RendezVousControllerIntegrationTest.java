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

        when(rendezVousService.createRendezvous(rendezVous)).thenReturn(rendezVous);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/public/rendezvous/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rendezVous)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"message\":\"Rendezvous created successfully!\"}"));
    }

    // Add more integration tests for other controller methods as needed
}
