package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import technical.test.api.entities.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

}