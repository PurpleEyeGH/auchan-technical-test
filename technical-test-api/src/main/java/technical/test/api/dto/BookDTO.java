package technical.test.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import technical.test.api.data.Gender;
import technical.test.api.entity.Book;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookDTO {

    private String id;
    private String title;
    private String authorName;
    private String authorGender;
    private String publicationDate;

    public static BookDTO fromEntity(Book bookEntity) {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(bookEntity.getId());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setPublicationDate(bookEntity.getPublicationDate().toString());
        bookDTO.setAuthorName(bookEntity.getAuthor().getName());
        bookDTO.setAuthorGender(bookEntity.getAuthor().getGender().genderName);

        return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO) {
        Book bookEntity = new Book();

        bookEntity.setId(bookDTO.getId());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(new AuthorDTO(bookDTO.getAuthorName(), Gender.valueOf(bookDTO.getAuthorGender().trim().toUpperCase())));
        bookEntity.setPublicationDate(LocalDate.parse(bookDTO.getPublicationDate()));

        return bookEntity;
    }

}
