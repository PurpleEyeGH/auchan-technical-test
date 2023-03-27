package technical.test.api.endpoints;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.entities.Author;
import technical.test.api.services.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("")
    public Mono<Author> createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @GetMapping("/{id}")
    public Mono<Author> getAuthorById(@PathVariable String id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("")
    public Flux<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAuthorById(@PathVariable String id) {
        return authorService.deleteAuthorById(id);
    }
}