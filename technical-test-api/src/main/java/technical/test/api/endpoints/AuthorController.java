package technical.test.api.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.dto.AuthorDTO;
import technical.test.api.document.Author;
import technical.test.api.services.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all-authors")
    public ResponseEntity<Flux<AuthorDTO>> getAllAuthors(){
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/find-author")
    public ResponseEntity<Mono<AuthorDTO>> getauthorById(@RequestParam String id){
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }



    @PostMapping("/create-author")
    public ResponseEntity<Mono<Author>> createAuthor(@RequestBody AuthorDTO authorDTO){
        return new ResponseEntity<>(authorService.saveAuthor(authorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update-author")
    public ResponseEntity<Mono<Author>> updateAuthor(@RequestBody AuthorDTO authorDTO){
        return new ResponseEntity<>(authorService.updateAuthor(authorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete-author")
    public  ResponseEntity<Mono<Void>> deleteAuthor(@RequestParam String authorId){
        return new ResponseEntity<>(authorService.deleteAuthor(authorId), HttpStatus.OK);
    }

}
