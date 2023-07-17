package technical.test.renderer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TechnicalTestRendererApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicalTestRendererApplication.class, args);
    }
    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

}
