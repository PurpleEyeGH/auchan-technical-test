package technical.test.api.endpoints;

import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.communication.model.BookApi;
import technical.test.api.config.ServiceConfiguration;
import technical.test.api.model.Book;
import technical.test.api.service.BookService;

@ExtendWith(SpringExtension.class)
@Import(ServiceConfiguration.class)
@WebFluxTest(BookController.class)
public class BookControllerTest {
	@Autowired
    private WebTestClient webClient;
	
	@MockBean
    private BookService bookService;

	@Test
    public void findAll() throws ParseException {

        Book book = new Book("Ca",new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"),List.of("Stephen King"));
        Book book2 = new Book("Mort sur le nil",new SimpleDateFormat("yyyy-MM-dd").parse("2013-01-01"),List.of("Agatha Christie"));

        when(bookService.getAll()).thenReturn(Flux.just(book,book2));

        webClient
                .get().uri("/api/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].title").isEqualTo("Ca")
                .jsonPath("$[0].publicationDate").isEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"))
                .jsonPath("$[0].authors.length()").isEqualTo(1)
                .jsonPath("$[0].authors[0]").isEqualTo("Stephen King")
                .jsonPath("$[1].title").isEqualTo("Mort sur le nil")
                .jsonPath("$[1].publicationDate").isEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse("2013-01-01"))
                .jsonPath("$[1].authors.length()").isEqualTo(1)
                .jsonPath("$[1].authors[0]").isEqualTo("Agatha Christie");
    }
	
	@Test
	public void save() throws ParseException {

		Book book = new Book("Ca", new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"), List.of("Stephen King"));

		when(bookService.save(ArgumentMatchers.any(Book.class))).thenReturn(Mono.just(book));

		webClient.post().uri("/api/books")
				.body(Mono.just(new BookApi("Ca", new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"), List.of("Stephen King"))), BookApi.class)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody()
				.jsonPath("$.title").isEqualTo("Ca")
				.jsonPath("$.publicationDate").isEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"))
				.jsonPath("$.authors.length()").isEqualTo(1)
				.jsonPath("$.authors[0]").isEqualTo("Stephen King");
	}
	
	@Test
	public void saveWithError() throws ParseException {

		webClient.post().uri("/api/books")
				.body(Mono.just(new BookApi("C", new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"), List.of("Stephen King"))), BookApi.class)
				.exchange()
				.expectStatus()
				.is5xxServerError();
	}
	
}
