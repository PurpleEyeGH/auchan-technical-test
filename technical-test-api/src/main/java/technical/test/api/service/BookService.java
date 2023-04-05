package technical.test.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.model.Book;
import technical.test.api.repository.BookRepository;
import technical.test.api.repository.dto.BookEntity;

/**
 * @author Antonin
 *
 *         Service gérant les livres
 */
@Service
public class BookService implements IBookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Mono<Book> save(Book book) {
		return bookRepository.save(modelMapper.map(book, BookEntity.class))
				.map(bookEntity -> modelMapper.map(bookEntity, Book.class));
	}

	@Override
	public Flux<Book> getAll() {
		return bookRepository.findAll().map(bookEntity -> modelMapper.map(bookEntity, Book.class));
	}

	@Override
	public Mono<Void> delete(String idBook) {
		return bookRepository.deleteById(idBook);
	}

}
