package technical.test.api.endpoints;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.entities.Book;
import technical.test.api.services.BookService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(BookController.class)
class BookControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                Book.builder()
                        .id("1")
                        .title("Book 1")
                        .publicationDate(LocalDateTime.now())
                        .authorId("1").build(),
                Book.builder()
                        .id("2")
                        .title("Book 2")
                        .publicationDate(LocalDateTime.now())
                        .authorId("1").build());

        given(bookService.getAllBooks()).willReturn(Flux.fromIterable(books));

        webTestClient.get()
                .uri("/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .isEqualTo(books);
    }

    @Test
    void testGetBookById() {
        Book book = Book.builder()
                .id("1")
                .title("Book 1")
                .publicationDate(LocalDateTime.now())
                .authorId("1").build();

        given(bookService.getBookById("1")).willReturn(Mono.just(book));

        webTestClient.get()
                .uri("/books/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .isEqualTo(book);


        verify(bookService, times(1)).getBookById(book.getId());
    }

    @Test
    void testCreateBook() {
        Book book = Book.builder()
                .title("Test Book")
                .authorId("1")
                .build();

        given(bookService.createBook(book)).willReturn(Mono.just(book));

        webTestClient.post()
                .uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class).isEqualTo(book);


        verify(bookService, times(1)).createBook(book);
    }


    @Test
    void testUpdateBook() {
        Book book = Book.builder()
                .id("1")
                .title("Book 1")
                .publicationDate(LocalDateTime.now())
                .authorId("1")
                .build();

        given(bookService.updateBook(book.getId(), book)).willReturn(Mono.just(book));

        webTestClient.put()
                .uri("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(book))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .isEqualTo(book);

        verify(bookService, times(1)).updateBook("1", book);
    }

    @Test
    void testDeleteBook() {
        given(bookService.deleteBookById("123")).willReturn(Mono.empty());

        webTestClient
                .delete()
                .uri("/books/{id}", "123")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        verify(bookService, times(1)).deleteBookById("123");
    }
}
