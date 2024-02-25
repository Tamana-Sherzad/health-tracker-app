package de.neuefische.backend.repository;
import de.neuefische.backend.model.HealthIssue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthIssueRepository extends MongoRepository<HealthIssue, String> {
    void deleteByName (String name);
}
