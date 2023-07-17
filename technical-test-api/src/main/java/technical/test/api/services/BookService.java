package technical.test.api.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.dto.AuthorDTO;
import technical.test.api.dto.input.BookInput;
import technical.test.api.dto.output.BookOutput;
import technical.test.api.document.Author;
import technical.test.api.document.Book;
import technical.test.api.model.BookBO;
import technical.test.api.repository.IBookRepository;

import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;


@Service
public class BookService {

    private final IBookRepository bookRepository;
    private final AuthorService authorService;


    public BookService(IBookRepository bookRepository, AuthorService authorService){
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Flux<BookOutput> getAllBooks(){
        return bookRepository.findAll().map(Book::toBO)
                .map(BookBO::toDTO);
    }

    public Mono<Book> saveNewBook(BookInput bookInput){
        Mono<Author> savedAuthorMono = authorService.getAuthorById(bookInput.getAuthorId())
                .flatMap(authorService::saveAuthor);

        return savedAuthorMono.flatMap(savedAuthor -> {
            Book book = new Book();
            book.setTitle(bookInput.getTitle());
            book.setPublicationDate(bookInput.getPublicationDate());
            book.setAuthor(savedAuthor);

            return bookRepository.save(book);
        });
    }

    public Mono<Book> updateBook(BookInput bookInput) {
        Mono<Author> savedAuthorMono = authorService.getAuthorById(bookInput.getAuthorId())
                .flatMap(authorService::saveAuthor);

        return savedAuthorMono.flatMap(savedAuthor -> bookRepository.findById(bookInput.getId()).flatMap(existingBook -> {
            existingBook.setTitle(bookInput.getTitle());
            existingBook.setPublicationDate(bookInput.getPublicationDate());
            existingBook.setAuthor(savedAuthor);

            return bookRepository.save(existingBook);
        }));
    }

    public Mono<Void> deleteBook(String bookId){
        return bookRepository.deleteById(bookId);
    }

}
