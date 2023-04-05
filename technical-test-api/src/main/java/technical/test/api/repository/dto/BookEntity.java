package technical.test.api.repository.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Antonin
 *
 *         Pojo d'un livre provenant de mongoDb
 */
@Document(value = "books")
@NoArgsConstructor
@Setter
@Getter
public class BookEntity {

	/**
	 * Identifiant technique du livre
	 */
	@Id
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

	public BookEntity(String title, Date publicationDate, List<String> authors) {
		this.title = title;
		this.publicationDate = publicationDate;
		this.authors = authors;
	}
}
