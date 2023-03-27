package technical.test.api.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.repository.AuthorRepository;
import technical.test.api.entities.Author;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Mono<Author> createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Mono<Author> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Mono<Void> deleteAuthorById(String id) {
        return authorRepository.deleteById(id);
    }
}
