package technical.test.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import technical.test.api.dto.BookDTO;
import technical.test.api.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<BookDTO> getBooks() {
        return bookRepository.findAll().map(BookDTO::fromEntity);
    }

}
