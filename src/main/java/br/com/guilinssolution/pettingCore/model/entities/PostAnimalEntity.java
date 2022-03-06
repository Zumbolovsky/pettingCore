package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import br.com.guilinssolution.pettingCore.model.enums.Species;
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
	@Column(name = "id_postAnimal", unique = true, nullable = false)
	private Integer idPostAnimal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usur", nullable = false)
	private UsurEntity usurEntity;

	@Column(name = "title_postAnimal", nullable = false, length = 30)
	private String titlePostAnimal;

	@Column(name = "description_postAnimal", nullable = false, length = 100)
	private String descriptionPostAnimal;

	@Enumerated(EnumType.STRING)
	@Column(name = "size_postAnimal", nullable = false, length = 7)
	private Size sizePostAnimal;

	@Column(name = "image_postAnimal", length = 50)
	private String imagePostAnimal;

	@Enumerated(EnumType.STRING)
	@Column(name = "species_postAnimal", nullable = false, length = 8)
	private Species speciesPostAnimal;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate_postAnimal", nullable = false)
	private Date createdDatePostAnimal;

	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate_postAnimal", nullable = false)
	private Date lastModifiedDatePostAnimal;

	public void update(PostAnimalEntity entity) {
		this.setUsurEntity(entity.getUsurEntity());
		this.setTitlePostAnimal(entity.getTitlePostAnimal());
		this.setDescriptionPostAnimal(entity.getDescriptionPostAnimal());
		this.setSizePostAnimal(entity.getSizePostAnimal());
		this.setImagePostAnimal(entity.getImagePostAnimal());
	}

}
