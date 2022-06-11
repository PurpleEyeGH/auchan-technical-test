package technical.test.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import technical.test.api.data.Gender;

import java.util.List;
import java.util.UUID;

@Data
@Document(collation = "authors")
public class Author {

    @Id
    private UUID id;
    private String name;
    private Gender gender;
    private List<Book> books;

}
