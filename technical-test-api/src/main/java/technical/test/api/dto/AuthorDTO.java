package technical.test.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technical.test.api.document.Author;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {
    private String id;
    private String name;
    private boolean gender;

    public Author toEntity(){
        Author author = new Author();
        author.setGender(gender);
        author.setId(id);
        author.setName(name);
        return author;
    }
}


