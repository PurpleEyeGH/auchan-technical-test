package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import technical.test.api.entity.Book;

import java.util.UUID;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, UUID> {
}
