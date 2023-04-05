package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import technical.test.api.repository.dto.AuthorEntity;

/**
 * @author Antonin
 *
 *         Reactive repository MongoDb pour les auteurs
 */
@Repository
public interface AuthorRepository extends ReactiveMongoRepository<AuthorEntity, String> {

}
