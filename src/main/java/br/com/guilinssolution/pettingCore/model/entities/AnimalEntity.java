package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idAnimal"})
@Table(name = "animal", schema = "dbo", catalog = "pettingdb")
public class AnimalEntity implements Serializable {

	private static final long serialVersionUID = -9017871019686804718L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_animal", unique = true, nullable = false)
	private Integer idAnimal;

	@Enumerated(EnumType.STRING)
	@Column(name = "species_animal", nullable = false, length = 8)
	private Species speciesAnimal;

	@Column(name = "breed_animal", nullable = false, length = 30)
	private String breedAnimal;

	public void update(AnimalEntity entity) {
		this.setBreedAnimal(entity.getBreedAnimal());
		this.setSpeciesAnimal(entity.getSpeciesAnimal());
	}

}
