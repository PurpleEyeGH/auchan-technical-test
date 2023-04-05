package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import technical.test.api.repository.dto.BookEntity;

/**
 * @author Antonin
 *
 *         Reactive repository MongoDb pour les livres
 */
@Repository
public interface BookRepository extends ReactiveMongoRepository<BookEntity, String> {

}
