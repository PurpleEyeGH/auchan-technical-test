package technical.test.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import technical.test.api.dto.AuthorDTO;

import java.time.LocalDate;

@Data
@Document(collection = "books")
@NoArgsConstructor
public class Book {

    @Id
    private String id;
    private String title;
    private LocalDate publicationDate;
    private AuthorDTO author;

}
