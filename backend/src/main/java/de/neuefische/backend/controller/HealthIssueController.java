package de.neuefische.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import de.neuefische.backend.model.HealthIssue;
import de.neuefische.backend.service.HealthIssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIssue);
    }

    @DeleteMapping("/remove-health-issue/{name}")
    public ResponseEntity<Void> removeHealthIssue(@PathVariable String name) {
        healthIssueService.removeHealthIssue(name);
        return ResponseEntity.noContent().build();
    }
}
