package technical.test.renderer.model;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Antonin
 *
 *         Données du formulaire d'ajout d'un livre
 */
@NoArgsConstructor
@Setter
@Getter
public class BookForm {

	/**
	 * Titre du livre
	 */
	@Size(min = 2, message = "Le titre doit contenir au moins 2 caractères")
	private String title;

	/**
	 * Date de publication
	 */
	@NotEmpty(message = "La date de publication doit être renseignée")
	private String publicationDate;

	/**
	 * Liste des auteurs du livre
	 */
	private List<String> bookAuthors;
}
