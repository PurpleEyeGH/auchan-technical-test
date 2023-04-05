package technical.test.api.endpoints;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import technical.test.api.communication.model.AuthorApi;
import technical.test.api.service.IAuthorService;

/**
 * @author Antonin
 * 
 *         Controller de la gestion des auteurs
 *
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

	/**
	 * {@link IAuthorService}
	 */
	@Autowired
	IAuthorService authorService;

	/**
	 * Mapper de DTO
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Récupère la liste de tous les auteurs
	 * 
	 * @return {@link Flux} contenant la liste des {@link AuthorApi}
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<AuthorApi> getAll() {
		return authorService.getAll().map(author -> modelMapper.map(author, AuthorApi.class));
	}

}
