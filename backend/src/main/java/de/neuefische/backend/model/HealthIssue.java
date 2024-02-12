package de.neuefische.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "health_issues")
public class HealthIssue {

    @Id
    private String id;

    private String name;

}
