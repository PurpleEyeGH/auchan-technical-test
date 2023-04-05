package technical.test.renderer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Antonin
 *
 *         Service de configuration des Beans
 */
@Configuration
public class WebClientConfiguration {

	/**
	 * Génère le bean permettant l'appel aux ressources externes
	 * 
	 * @return Le bean de {@link WebClient}
	 */
	@Bean
	public WebClient localApiClient() {
		return WebClient.builder().baseUrl("http://localhost:8081/api/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
}
