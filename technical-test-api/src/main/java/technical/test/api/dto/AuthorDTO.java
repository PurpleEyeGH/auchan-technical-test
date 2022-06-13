package technical.test.api.dto;

import lombok.Data;
import technical.test.api.data.Gender;

@Data
public class AuthorDTO {

    private String name;
    private Gender gender;

    public AuthorDTO(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

}
