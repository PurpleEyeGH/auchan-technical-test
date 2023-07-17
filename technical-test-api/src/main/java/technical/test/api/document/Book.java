package technical.test.api.document;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import technical.test.api.model.BookBO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Document("book")
public class Book {
    @Id
    private String id;
    private String title;
    @BsonProperty(value = "publication_date")
    private LocalDate publicationDate;

    private Author author;

    public BookBO toBO(){
        BookBO bookBO = new BookBO();
        bookBO.setTitle(title);
        bookBO.setAuthor(author);
        bookBO.setPublicationDate(publicationDate);
        return bookBO;
    }
}


