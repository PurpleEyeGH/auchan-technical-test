package technical.test.api.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.dto.AuthorDTO;
import technical.test.api.document.Author;
import technical.test.api.model.AuthorBO;
import technical.test.api.repository.IAuthorRepository;
import technical.test.api.repository.IBookRepository;

@Service
public class AuthorService {
    private final IAuthorRepository authorRepository;
    private final IBookRepository bookRepository;

    public AuthorService(IAuthorRepository authorRepository, IBookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Mono<AuthorDTO> getAuthorById(String id){
        return authorRepository.findById(id)
                .map(Author::toBO)
                .map(AuthorBO::toDTO);
    }

    public Flux<AuthorDTO> getAllAuthors(){
        return authorRepository.findAll()
                .map(Author::toBO)
                .map(AuthorBO::toDTO);
    }

    public Mono<Author> saveAuthor(AuthorDTO authorDTO){
        if(authorDTO.getId() == null){
            return authorRepository.save(authorDTO.toEntity());
        }
        return authorRepository.findById(authorDTO.getId())
                .switchIfEmpty(authorRepository.save(authorDTO.toEntity()));
    }

    public Mono<Author> updateAuthor(AuthorDTO authorDTO){
        return authorRepository.findById(authorDTO.getId())
                .flatMap(existingAuthor -> {
                    existingAuthor.setName(authorDTO.getName());
                    existingAuthor.setGender(authorDTO.isGender());
                    return authorRepository.save(existingAuthor);
                });
    }

    public Mono<Void> deleteAuthor(String authorId){
        return bookRepository.deleteByAuthorId(authorId)
                .then(authorRepository.deleteById(authorId));
    }
}
