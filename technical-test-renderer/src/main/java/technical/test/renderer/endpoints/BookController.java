package technical.test.renderer.endpoints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import technical.test.api.communication.model.AuthorApi;
import technical.test.api.communication.model.BookApi;
import technical.test.renderer.model.BookForm;

/**
 * @author Antonin
 * 
 *         Controller de la gestion des livres
 *
 */
@Controller
public class BookController {

	/**
	 * Page html listant les livres
	 */
	private static String HOME_PAGE = "index";
	/**
	 * Redirection vers l'url d'accueil
	 */
	private static String HOME_URL_REDIRECT = "redirect:/";
	/**
	 * Page html d'ajout de livre
	 */
	private static String ADD_BOOK_PAGE = "addbook";

	/**
	 * Le {@link WebClient} permettant les appels aux services distants
	 */
	@Autowired
	private WebClient apiClient;

	/**
	 * Récupère la liste de tous les livres et les affiches
	 * 
	 * @param model {@link }
	 * @return La page à afficher
	 */
	@GetMapping("/")
	public String displayBooks(final Model model) {
		IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(
				apiClient.get().uri("books").retrieve().bodyToFlux(BookApi.class), 1);

		model.addAttribute("books", reactiveDataDrivenMode);

		return HOME_PAGE;
	}

	/**
	 * Récupère la liste de tous les auteurs et affiche la page d'ajout de livre
	 * 
	 * @param model {@link Model}
	 * @return La page à afficher
	 */
	@GetMapping("/display_book_form")
	public String showAddBookForm(final Model model) {

		model.addAttribute("bookForm", new BookForm());

		IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(
				apiClient.get().uri("authors").retrieve().bodyToFlux(AuthorApi.class), 1);

		model.addAttribute("listAuthors", reactiveDataDrivenMode);

		return ADD_BOOK_PAGE;
	}

	/**
	 * Valide les données du formulaire et appel le service d'ajout de livre
	 * 
	 * @param bookToAdd Les données à valider
	 * @param result    Le {@link BindingResult} permettant de détecter les erreurs
	 *                  de validation
	 * @param model     {@link Model}
	 * @return La redirection d'URL
	 * @throws ParseException
	 */
	@PostMapping("/add")
	public String addBook(@Valid BookForm bookToAdd, BindingResult result, Model model) throws ParseException {
		if (result.hasErrors()) {
			IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(
					apiClient.get().uri("authors").retrieve().bodyToFlux(AuthorApi.class), 1);

			model.addAttribute("listAuthors", reactiveDataDrivenMode);
			return ADD_BOOK_PAGE;
		}
		BookApi book = new BookApi(bookToAdd.getTitle(),
				new SimpleDateFormat("yyyy-MM-dd").parse(bookToAdd.getPublicationDate()), bookToAdd.getBookAuthors());

		apiClient.post().uri("books").body(Mono.just(book), BookApi.class).retrieve().bodyToMono(BookApi.class)
				.subscribe();

		return HOME_URL_REDIRECT;
	}

	/**
	 * Supprime un livre
	 * 
	 * @param bookId Les données à valider
	 * @return La redirection d'URL
	 */
	@GetMapping(value = "/delete_book/{bookId}")
	public String handleDeleteUser(@PathVariable String bookId) {
		apiClient.delete().uri("books/{id}", Collections.singletonMap("id", bookId)).retrieve().bodyToMono(Void.class)
				.subscribe();

		return HOME_URL_REDIRECT;
	}

}
