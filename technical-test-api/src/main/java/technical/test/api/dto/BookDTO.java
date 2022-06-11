package technical.test.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import technical.test.api.entity.Book;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookDTO {

    private String id;
    private String title;
    private String authorName;
    private LocalDateTime publicationDate;

    public static BookDTO fromEntity(Book bookEntity) {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(bookEntity.getId());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setPublicationDate(bookEntity.getPublicationDate());
        bookDTO.setAuthorName(bookEntity.getAuthor().getName());

        return bookDTO;
    }

}
