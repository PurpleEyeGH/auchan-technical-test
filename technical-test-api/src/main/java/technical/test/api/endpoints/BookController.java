package technical.test.api.endpoints;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.communication.model.BookApi;
import technical.test.api.model.Book;
import technical.test.api.service.IBookService;

/**
 * @author Antonin
 * 
 *         Controller de la gestion des livres
 *
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

	/**
	 * {@link IBookService}
	 */
	@Autowired
	IBookService bookService;

	/**
	 * Mapper de DTO
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Récupère la liste de tous les livres
	 * 
	 * @return {@link Flux} contenant la liste des {@link BookApi}
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<BookApi> getAll() {
		return bookService.getAll().map(book -> modelMapper.map(book, BookApi.class));
	}

	/**
	 * Enregistre un nouveau livre
	 * 
	 * @param book Le livre à enregistrer
	 * @return {@link Mono} contenant le livre enregistré {@link BookApi}
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<BookApi> createBook(@RequestBody BookApi bookApi) {
		Preconditions.checkNotNull(bookApi, "Le livre ne doit pas être null");
		Preconditions.checkNotNull(bookApi.getTitle(), "Le titre du livre ne doit pas être null");
		Preconditions.checkArgument((bookApi.getTitle().trim().length() >= 2),
				"Le livre doit contenir au moins 2 caractères");
		Preconditions.checkNotNull(bookApi.getPublicationDate(), "La date de publication ne doit pas être null");
		
		return bookService.save(new Book(bookApi.getTitle(), bookApi.getPublicationDate(), bookApi.getAuthors()))
				.map(book -> modelMapper.map(book, BookApi.class));
	}

	/**
	 * Supprime un livre
	 * 
	 * @param idBook L'identifiant technique du livre
	 * @return {@link Mono} vide
	 */
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable("id") String idBook) {
		return bookService.delete(idBook);
	}
}
