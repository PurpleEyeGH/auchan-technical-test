package technical.test.api.communication.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Antonin
 *
 *         Pojo d'un livre utilisé dans les communications entre applications
 */
@Getter
@Setter
@NoArgsConstructor
public class BookApi {

	/**
	 * Identifiant technique du livre
	 */
	private String id;

	/**
	 * Titre du livre
	 */
	private String title;

	/**
	 * Date de publication
	 */
	private Date publicationDate;

	/**
	 * Liste des auteurs du livre
	 */
	private List<String> authors;
	
	public BookApi(String title, Date publicationDate, List<String> authors) {
		this.title = title;
		this.publicationDate = publicationDate;
		this.authors=authors;
	}
}
