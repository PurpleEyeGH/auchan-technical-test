package technical.test.api.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.model.Book;
import technical.test.api.repository.dto.BookEntity;

/**
 * @author Antonin
 * 
 *         Interface du service gérant les livres
 *
 */
public interface IBookService {

	/**
	 * Récupère la liste de tous les livres
	 * 
	 * @return {@link Flux} contenant la liste des {@link Book}
	 */
	Flux<Book> getAll();

	/**
	 * Enregistre un nouveau livre
	 * 
	 * @param book Le livre à enregistrer
	 * @return {@link Mono} contenant le livre après l'enregistrement
	 *         {@link BookEntity}
	 */
	Mono<Book> save(Book book);

	/**
	 * Supprime un livre
	 * 
	 * @param idBook L'identifiant technique du livre
	 * @return {@link Mono} vide
	 */
	Mono<Void> delete(String idBook);

}