package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import technical.test.api.entity.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
