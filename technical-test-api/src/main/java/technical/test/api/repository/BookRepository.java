package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import technical.test.api.entities.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}