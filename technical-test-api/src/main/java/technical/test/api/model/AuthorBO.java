package technical.test.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technical.test.api.dto.AuthorDTO;

@Getter
@Setter
@NoArgsConstructor
public class AuthorBO {
        private String id;

        private String name;

        private boolean gender;

        public AuthorDTO toDTO() {
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(id);
                authorDTO.setName(name);
                authorDTO.setGender(gender);
                return authorDTO;
        }
}


