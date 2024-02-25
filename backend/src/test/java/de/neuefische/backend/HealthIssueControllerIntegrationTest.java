package de.neuefische.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.HealthIssue;
import de.neuefische.backend.service.HealthIssueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class HealthIssueControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HealthIssueService healthIssueService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllHealthIssues() throws Exception {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        HealthIssue issue1 = new HealthIssue(uuid1, "Headache");
        HealthIssue issue2 = new HealthIssue(uuid2, "Fever");

        when(healthIssueService.getAllHealthIssues()).thenReturn(Arrays.asList(issue1, issue2));

        // Perform GET request and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/health-tracker/health-issues")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert response body using object mapping
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(issue1, issue2))));
    }

    @DirtiesContext
    @Test
    public void testAddHealthIssue() throws Exception {
        UUID uuid3 = UUID.randomUUID();
        HealthIssue newIssue = new HealthIssue(uuid3, "Cough");
        when(healthIssueService.addHealthIssue(any())).thenReturn(newIssue);

        // Perform POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/health-tracker/add-health-issue")
                        .content(objectMapper.writeValueAsString(newIssue))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert response body using object mapping
                .andExpect(content().json(objectMapper.writeValueAsString(newIssue)));
    }

    @DirtiesContext
    @Test
    public void testRemoveHealthIssue() throws Exception {
        String name = "Fever"; // Replace with the actual name of the health issue

        // Perform DELETE Request with path variable
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/health-tracker/remove-health-issue/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
