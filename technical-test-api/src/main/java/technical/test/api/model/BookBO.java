package technical.test.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technical.test.api.dto.output.BookOutput;
import technical.test.api.document.Author;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookBO {
    private String title;
    private LocalDate publicationDate;
    private Author author;

    public BookOutput toDTO(){
        BookOutput bookOutput = new BookOutput();
        bookOutput.setTitle(title);
        bookOutput.setAuthorName(author.getName());
        bookOutput.setPublicationDate(publicationDate);
        return bookOutput;
    }
}


