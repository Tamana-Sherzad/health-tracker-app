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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HealthIssueControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HealthIssueService healthIssueService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
     void testGetAllHealthIssues() throws Exception {

        // Mock data
        HealthIssue issue1 = new HealthIssue("1","Headache");
        HealthIssue issue2 = new HealthIssue("2","Fever");



        when(healthIssueService.getAllHealthIssues()).thenReturn(Arrays.asList(issue1, issue2));

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/health-tracker/health-issues")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert response body if needed
                .andExpect(content().json("[{\"id\":\"1\",\"name\":\"Headache\"},{\"id\":\"2\",\"name\":\"Fever\"}]"));
    }

    @Test
     void testAddHealthIssue() throws Exception {
        // Mock data
        HealthIssue newIssue = new HealthIssue("3","Cough");
        when(healthIssueService.addHealthIssue(any())).thenReturn(newIssue);

        // Perform POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/health-tracker/add-health-issue")
                        .content(objectMapper.writeValueAsString(newIssue))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert response body
                .andExpect(content().json("{\"id\":\"3\",\"name\":\"Cough\"}"));
    }

    @Test
    void testRemoveHealthIssue() throws Exception {
        // Perform DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/health-tracker/remove-health-issue/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
