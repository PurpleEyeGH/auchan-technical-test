package technical.test.api.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.dto.BookDTO;
import technical.test.api.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Flux<BookDTO>> books() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping("/add")
    public Mono<BookDTO> addBook(@RequestBody Mono<BookDTO> bookDTOMono) {
        return bookService.saveBook(bookDTOMono);
    }

}
