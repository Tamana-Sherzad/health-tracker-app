package de.neuefische.backend;

import de.neuefische.backend.model.HealthIssue;
import de.neuefische.backend.repository.HealthIssueRepository;
import de.neuefische.backend.service.HealthIssueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class HealthIssueServiceUnitTest {

    @Autowired
    private HealthIssueService healthIssueService;

    @MockBean
    private HealthIssueRepository healthIssueRepository;

    @Test
    void testGetAllHealthIssues() {
        // Create a HealthIssue instance
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        HealthIssue issue1 = new HealthIssue(uuid1,"Headache");
        HealthIssue issue2 = new HealthIssue(uuid2,"Fever");

        // Mock the HealthIssueRepository
        when(healthIssueRepository.findAll()).thenReturn(Arrays.asList(issue1, issue2));

        // Call the service method to get all health issues
        List<HealthIssue> healthIssues = healthIssueService.getAllHealthIssues();

        // Assertions
        assertEquals(2, healthIssues.size());
        assertEquals("Headache", healthIssues.get(0).getName());
        assertEquals("Fever", healthIssues.get(1).getName());
    }

    @Test
    void testAddHealthIssue() {
        // Create a HealthIssue instance
        UUID uuid = UUID.randomUUID();
        HealthIssue issue = new HealthIssue(uuid,"Headache");

        // Mock the behavior of save() method in HealthIssueRepository
        when(healthIssueRepository.save(issue)).thenReturn(issue);

        // Call the service method to add the health issue
        HealthIssue savedIssue = healthIssueService.addHealthIssue(issue);

        // Assert that the saved issue matches the original issue
        assertEquals(issue, savedIssue);
    }

    @Test
    void testRemoveHealthIssue() {
        // Define the name to be removed
        String name = "Fever";

        // Call the service method to remove the health issue
        healthIssueService.removeHealthIssue(name);

        // Verify that the deleteById method of the repository is called with the correct issueId
        verify(healthIssueRepository).deleteByName(name);
    }

}



