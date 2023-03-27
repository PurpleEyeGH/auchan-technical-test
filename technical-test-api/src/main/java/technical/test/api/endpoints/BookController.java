package technical.test.api.endpoints;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.entities.Book;
import technical.test.api.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public Mono<Book> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("")
    public Flux<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBookById(@PathVariable String id) {
        return bookService.deleteBookById(id);
    }

    @PutMapping("/{id}")
    public Mono<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }
}