package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idPostAnimal"})
@EntityListeners({AuditingEntityListener.class})
@Table(name = "postAnimal", schema = "dbo", catalog = "pettingdb")
public class PostAnimalEntity implements Serializable {

	private static final long serialVersionUID = -6355063969931730591L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postAnimal", unique = true)
	private Integer idPostAnimal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_animal")
	private AnimalEntity animalEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usur")
	private UsurEntity usurEntity;

	@Column(name = "title_postAnimal", nullable = false, length = 30)
	private String titlePostAnimal;

	@Column(name = "description_postAnimal", nullable = false, length = 100)
	private String descriptionPostAnimal;

	@Enumerated(EnumType.STRING)
	@Column(name = "size_postAnimal", nullable = false, length = 7)
	private Size sizePostAnimal;

	@Column(name = "image_postAnimal", nullable = false, length = 50)
	private String imagePostAnimal;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate_postAnimal")
	private Date createdDatePostAnimal;

	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate_postAnimal")
	private Date lastModifiedDatePostAnimal;

	public void update(PostAnimalEntity entity) {
		this.setAnimalEntity(entity.getAnimalEntity());
		this.setUsurEntity(entity.getUsurEntity());
		this.setTitlePostAnimal(entity.getTitlePostAnimal());
		this.setDescriptionPostAnimal(entity.getDescriptionPostAnimal());
		this.setSizePostAnimal(entity.getSizePostAnimal());
		this.setImagePostAnimal(entity.getImagePostAnimal());
	}

}
