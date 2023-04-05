package technical.test.api.service;

import reactor.core.publisher.Flux;
import technical.test.api.model.Author;

/**
 * @author Antonin
 * 
 *         Interface du service gérant les auteurs
 *
 */
public interface IAuthorService {

	/**
	 * Récupère la liste de tous les auteurs
	 * 
	 * @return {@link Flux} contenant la liste des {@link Author}
	 */
	Flux<Author> getAll();

}