package technical.test.api.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import technical.test.api.model.AuthorBO;


@Getter
@Setter
@NoArgsConstructor
@Document("author")
public class Author {
    @Id
    private String id;

    private String name;

    private boolean gender;
    public AuthorBO toBO(){
        AuthorBO authorBO = new AuthorBO();
        authorBO.setId(id);
        authorBO.setName(name);
        authorBO.setGender(gender);
        return authorBO;
    }

}
