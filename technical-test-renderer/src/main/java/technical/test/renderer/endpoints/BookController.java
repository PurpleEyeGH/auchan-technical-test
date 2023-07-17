package technical.test.renderer.endpoints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.dto.AuthorDTO;
import technical.test.api.dto.input.BookInput;
import technical.test.api.dto.output.BookOutput;


@Controller
public class BookController {

    @Value("${api.url}")
    private String apiUrl;

    private final WebClient webClient;

    public BookController(WebClient webClient) {
        this.webClient = webClient;
    }
    @GetMapping("/")
    public Mono<String> getBooks(Model model) {
        Flux<BookOutput> booksFlux = webClient.get()
                .uri(apiUrl + "/book/all-books")
                .retrieve()
                .bodyToFlux(BookOutput.class);

        return booksFlux.collectList()
                .doOnSuccess(books -> model.addAttribute("books", books))
                .thenReturn("index");
    }

    @GetMapping("/add-book-form")
        public Mono<String> getAuthors(Model model){
        model.addAttribute("bookInput", new BookInput());

        Flux<AuthorDTO> authorFlux = webClient.get()
                .uri(apiUrl + "/author/all-authors")
                .retrieve()
                .bodyToFlux(AuthorDTO.class);


        return authorFlux.collectList()
                .doOnSuccess(authors -> model.addAttribute("authors", authors))
                .thenReturn("add-book");
        }


    @PostMapping("/addBook")
    public Mono<String> addBook(@ModelAttribute BookInput bookInput) {
        Mono<BookInput> savedBookMono = webClient.post()
                .uri(apiUrl + "/book/create-book")
                .body(Mono.just(bookInput), BookInput.class)
                .retrieve()
                .bodyToMono(BookInput.class);

        return savedBookMono.map(savedBook -> "redirect:/")
                .onErrorResume(throwable -> Mono.just("error"));
    }
}
