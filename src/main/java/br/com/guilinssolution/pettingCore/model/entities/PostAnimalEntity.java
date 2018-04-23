package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"idPostAnimal"})
@Table(name = "postAnimal", schema = "dbo", catalog = "pettingdb")
public class PostAnimalEntity implements Serializable {

	private static final long serialVersionUID = -6355063969931730591L;

	@Id
	@Column(name = "id_postAnimal", unique = true, nullable = false)
	private Integer idPostAnimal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_animal", nullable = false)
	private AnimalEntity animalEntity;

	@Column(name = "title_postAnimal", nullable = false, length = 30)
	private String titlePostAnimal;

	@Column(name = "description_postAnimal", nullable = false, length = 100)
	private String descriptionPostAnimal;

	@Column(name = "size_postAnimal", nullable = false)
	private Integer sizePostAnimal;

	@Column(name = "image_postAnimal", nullable = false, length = 50)
	private String imagePostAnimal;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postAnimalEntity")
	private Set<UsurEntity> usurEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postAnimalEntity")
	private Set<ContributionEntity> contributionEntities;

	public void update(PostAnimalEntity entity) {
		this.setIdPostAnimal(entity.getIdPostAnimal());
		this.setTitlePostAnimal(entity.getTitlePostAnimal());
		this.setDescriptionPostAnimal(entity.getDescriptionPostAnimal());
		this.setSizePostAnimal(entity.getSizePostAnimal());
		this.setImagePostAnimal(entity.getImagePostAnimal());
		this.setAnimalEntity(entity.getAnimalEntity());
	}

}
