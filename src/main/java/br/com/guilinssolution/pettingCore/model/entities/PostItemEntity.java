package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;

import javax.persistence.*;

import br.com.guilinssolution.pettingCore.model.enums.Type;
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
@EqualsAndHashCode(of = {"idPostItem"})
@Table(name = "postItem", schema = "dbo", catalog = "pettingdb")
public class PostItemEntity implements Serializable {

	private static final long serialVersionUID = 1651057555186766600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postItem", unique = true, nullable = false)
	private Integer idPostItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_animal", nullable = false)
	private AnimalEntity animalEntity;

	@Column(name = "title_postItem", nullable = false, length = 30)
	private String titlePostItem;

	@Column(name = "description_postItem", nullable = false, length = 100)
	private String descriptionPostItem;

	@Column(name = "image_postItem", nullable = false, length = 50)
	private String imagePostItem;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_postItem", nullable = false, length = 7)
	private Type typePostItem;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postItemEntity")
//	private List<ContributionEntityLite> contributionEntities;
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postItemEntity")
//	private List<UsurEntityLite> usurEntities;

	public void update(PostItemEntity entity) {
		this.setIdPostItem(entity.getIdPostItem());
		this.setTitlePostItem(entity.getTitlePostItem());
		this.setDescriptionPostItem(entity.getDescriptionPostItem());
		this.setImagePostItem(entity.getImagePostItem());
		this.setTypePostItem(entity.getTypePostItem());
		this.setAnimalEntity(entity.getAnimalEntity());
	}

}
