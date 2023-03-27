package technical.test.api.entities;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@Jacksonized
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String title;
    private LocalDateTime publicationDate;
    private String authorId;

}