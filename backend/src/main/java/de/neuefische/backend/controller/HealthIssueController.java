package de.neuefische.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import de.neuefische.backend.model.HealthIssue;
import de.neuefische.backend.service.HealthIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/health-tracker")
public class HealthIssueController {

    @Autowired
    private HealthIssueService healthIssueService;

    @GetMapping("/health-issues")
    public List<HealthIssue> getAllHealthIssues() {
        return healthIssueService.getAllHealthIssues();
    }


    @PostMapping("/add-health-issue")
    public ResponseEntity<HealthIssue> addHealthIssue(@RequestBody HealthIssue issue) {
        HealthIssue savedIssue = healthIssueService.addHealthIssue(issue);
        return ResponseEntity.ok(savedIssue);
    }

    @DeleteMapping("/remove-health-issue/{issueId}")
    public ResponseEntity<Void> removeHealthIssue(@PathVariable String issueId) {
        healthIssueService.removeHealthIssue(issueId);
        return ResponseEntity.noContent().build();
    }



}
