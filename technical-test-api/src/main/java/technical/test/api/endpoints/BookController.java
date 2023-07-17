package technical.test.api.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.dto.input.BookInput;
import technical.test.api.dto.output.BookOutput;
import technical.test.api.document.Book;
import technical.test.api.services.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all-books")
    public ResponseEntity<Flux<BookOutput>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping("/create-book")
    public ResponseEntity<Mono<Book>> createBook(@RequestBody BookInput bookInput) {
        return new ResponseEntity<>(bookService.saveNewBook(bookInput), HttpStatus.CREATED);
    }

    @PutMapping("/update-book")
    public ResponseEntity<Mono<Book>> updateBook(@RequestBody BookInput bookInput) {
        return new ResponseEntity<>(bookService.updateBook(bookInput), HttpStatus.OK);
    }

    @DeleteMapping("/delete-book")
    public ResponseEntity<Mono<Void>> deleteBook(@RequestParam String bookId){
        return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
    }

}
