package technical.test.api.communication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Antonin
 *
 *         Pojo d'un auteur utilisé dans les communications entre applications
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthorApi {

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


	public AuthorApi(String id,String name, String gender) {
		this.id=id;
		this.name = name;
		this.gender = gender;
	}
}