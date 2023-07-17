package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import technical.test.api.document.Book;

@Repository
public interface IBookRepository extends ReactiveMongoRepository<Book, String> {
    public Mono<Void> deleteByAuthorId(String authorId);
}
