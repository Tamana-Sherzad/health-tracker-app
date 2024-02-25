package de.neuefische.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Data
@Document(collection = "health_issues")
public class HealthIssue {

    @Id
    private UUID id;

    private String name;

   public HealthIssue(UUID id, String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

}
