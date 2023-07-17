package technical.test.api.dto.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookOutput {
    private String title;
    private LocalDate publicationDate;
    private String authorName;

}
