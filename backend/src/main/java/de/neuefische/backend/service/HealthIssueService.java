package de.neuefische.backend.service;

import de.neuefische.backend.model.HealthIssue;
import de.neuefische.backend.repository.HealthIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthIssueService {


    private final HealthIssueRepository healthIssueRepository;

    @Autowired
    public HealthIssueService(HealthIssueRepository healthIssueRepository) {
        this.healthIssueRepository = healthIssueRepository;
    }

    public List<HealthIssue> getAllHealthIssues() {
        return healthIssueRepository.findAll();
    }

    public HealthIssue addHealthIssue(HealthIssue issue) {
        return healthIssueRepository.save(issue);
    }

    public void removeHealthIssue(String issueId) {
        healthIssueRepository.deleteById(issueId);
    }
}
