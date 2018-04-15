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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PostItemEntity")
@Table(name = "postItem", schema = "dbo", catalog = "pettingdb")
public class PostItemEntity implements Serializable {

	private static final long serialVersionUID = 1651057555186766600L;

	@Id
	@Column(name = "id_postItem", unique = true, nullable = false)
	private int idPostItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_animal", nullable = false)
	private AnimalEntity animalEntity;

	@Column(name = "title_postItem", nullable = false, length = 30)
	private String titlePostItem;

	@Column(name = "description_postItem", nullable = false, length = 100)
	private String descriptionPostItem;

	@Column(name = "image_postItem", nullable = false, length = 50)
	private String imagePostItem;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postItemEntity")
	private Set<ContributionEntity> contributionEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postItemEntity")
	private Set<UsurEntity> usurEntities;

}
