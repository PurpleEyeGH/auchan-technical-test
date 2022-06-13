package technical.test.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

    public Mono<BookDTO> saveBook(Mono<BookDTO> bookDTOMono) {
        return bookDTOMono.map(BookDTO::toEntity).flatMap(bookRepository::insert).map(BookDTO::fromEntity);
    }

}
