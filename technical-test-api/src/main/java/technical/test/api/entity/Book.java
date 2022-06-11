package technical.test.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collation = "books")
public class Book {

    @Id
    private UUID id;
    private String title;
    private String publicationDate;
    private Author author;

}
