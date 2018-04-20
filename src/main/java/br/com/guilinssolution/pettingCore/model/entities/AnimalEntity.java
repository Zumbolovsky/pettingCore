package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@EqualsAndHashCode(of= {"idAnimal"})
@Table(name = "animal", schema = "dbo", catalog = "pettingdb")
public class AnimalEntity implements Serializable {

	private static final long serialVersionUID = -9017871019686804718L;

	@Id
	@Column(name = "id_animal", unique = true, nullable = false)
	private Integer idAnimal;

	@Column(name = "species_animal", nullable = false, length = 30)
	private String speciesAnimal;

	@Column(name = "breed_animal", nullable = false, length = 30)
	private String breedAnimal;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "animalEntity")
	private Set<PostAnimalEntity> postAnimalEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "animalEntity")
	private Set<PostItemEntity> postItemEntities;

}
