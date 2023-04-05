package technical.test.api.repository.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Antonin
 *
 *         Pojo d'un auteur provenant de mongoDb
 */
@Document(value = "authors")
@NoArgsConstructor
@Setter
@Getter
public class AuthorEntity {

	/**
	 * Identifiant technique de l'auteur
	 */
	@Id
	private String id;

	/**
	 * Nom de l'auteur
	 */
	private String name;

	/**
	 * Genre de l'auteur
	 */
	private String gender;

	public AuthorEntity(String id, String name, String gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
}
