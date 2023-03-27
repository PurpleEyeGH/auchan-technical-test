package technical.test.api.entities;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Jacksonized
@Document(collection = "authors")
public class Author {

    @Id
    private String id;
    private String name;
    private String email;
}