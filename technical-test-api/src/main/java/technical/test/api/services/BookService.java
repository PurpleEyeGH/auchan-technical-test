package technical.test.api.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.entities.Book;
import technical.test.api.repository.AuthorRepository;
import technical.test.api.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Mono<Book> createBook(Book book) {
        return authorRepository.findById(book.getAuthorId())
                .flatMap(author -> {
                    book.setAuthorId(author.getId());
                    return bookRepository.save(book);
                });
    }

    public Mono<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Mono<Void> deleteBookById(String id) {
        return bookRepository.deleteById(id);
    }

    public Mono<Book> updateBook(String id, Book book) {
        return bookRepository.findById(id)
                .flatMap(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setPublicationDate(book.getPublicationDate());
                    existingBook.setAuthorId(book.getAuthorId());
                    return bookRepository.save(existingBook);
                });
    }

}