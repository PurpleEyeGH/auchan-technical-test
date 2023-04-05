package technical.test.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import technical.test.api.model.Author;
import technical.test.api.repository.AuthorRepository;

/**
 * @author Antonin
 *
 *         Service gérant les auteurs
 */
@Service
public class AuthorService implements IAuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Flux<Author> getAll() {
		return authorRepository.findAll().map(authorEntity -> modelMapper.map(authorEntity, Author.class));
	}
}
