package technical.test.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Antonin
 *
 *         Pojo d'un auteur pour la couche métier
 */
@Getter
@Setter
@NoArgsConstructor
public class Author {
	
	/**
	 * Identifiant technique de l'auteur
	 */
	private String id;

	/**
	 * Nom de l'auteur
	 */
	private String name;

	/**
	 * Genre de l'auteur
	 */
	private String gender;

	public Author(String name, String gender) {
		this.name = name;
		this.gender = gender;
	}
}