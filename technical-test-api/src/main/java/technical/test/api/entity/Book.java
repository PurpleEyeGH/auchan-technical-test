package technical.test.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "books")
public class Book {

    @Id
    private String id;
    private String title;
    private LocalDateTime publicationDate;
    private Author author;

}
