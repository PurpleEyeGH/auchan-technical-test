package technical.test.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Antonin
 *
 *         Service de configuration des Beans
 */
@Configuration
public class ServiceConfiguration {

	/**
	 * Génère le bean permettant la transformation des DTOs
	 * 
	 * @return Le bean de {@link ModelMapper}
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
