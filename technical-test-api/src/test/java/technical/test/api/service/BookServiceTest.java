package technical.test.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.common.collect.Iterables;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import technical.test.api.model.Book;
import technical.test.api.repository.BookRepository;
import technical.test.api.repository.dto.BookEntity;

@ExtendWith(SpringExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;
	
	@Spy
	private ModelMapper modelMapper = new ModelMapper();

	@Test
	public void findAll() throws ParseException {
		BookEntity book = new BookEntity("Ca", new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"),
				List.of("Stephen King"));
		BookEntity book2 = new BookEntity("Mort sur le nil", new SimpleDateFormat("yyyy-MM-dd").parse("2013-01-01"),
				List.of("Agatha Christie"));

		Mockito.when(bookRepository.findAll()).thenReturn(Flux.just(book, book2));
		Flux<Book> bookFlux = bookService.getAll();

		StepVerifier.create(bookFlux).consumeNextWith(currentBook -> {
					assertEquals(currentBook.getTitle(), "Ca");
					assertEquals(Iterables.getOnlyElement(currentBook.getAuthors()), "Stephen King");
				}).consumeNextWith(currentBook -> {
					assertEquals(currentBook.getTitle(), "Mort sur le nil");
					assertEquals(Iterables.getOnlyElement(currentBook.getAuthors()), "Agatha Christie");
				})
				.verifyComplete();
	}

}
