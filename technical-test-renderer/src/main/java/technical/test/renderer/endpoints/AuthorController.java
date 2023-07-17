package technical.test.renderer.endpoints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import technical.test.api.dto.AuthorDTO;

@Controller
public class AuthorController {
    @Value("${api.url}")
    String apiUrl;

    private final WebClient webClient;

    public AuthorController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/add-author-form")
    public String displayForm(Model model){
        model.addAttribute("author", new AuthorDTO());
        return "add-author";
    }

    @PostMapping("/addAuthor")
    public Mono<String> addAuthor(@ModelAttribute AuthorDTO authorDTO){
        Mono<AuthorDTO> savedAuthorMono = webClient.post()
                .uri(apiUrl + "/author/create-author")
                .body(Mono.just(authorDTO), AuthorDTO.class)
                .retrieve()
                .bodyToMono(AuthorDTO.class);

        return savedAuthorMono.map(savedAuthor -> "redirect:/")
                .onErrorResume(throwable -> Mono.just("error"));
    }
}
