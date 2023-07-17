package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import technical.test.api.document.Author;

@Repository
public interface IAuthorRepository extends ReactiveMongoRepository<Author, String> {
}
