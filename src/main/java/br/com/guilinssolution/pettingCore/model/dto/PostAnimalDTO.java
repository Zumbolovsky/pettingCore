package br.com.guilinssolution.pettingCore.model.dto;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PostAnimalDTO {

	@NotNull
	private int idPostAnimal;

	@NotNull
	@ApiModelProperty(hidden = true)
	private AnimalDTO animalDTO;

	@NotNull
	@Length(max = 30)
	private String titlePostAnimal;

	@NotNull
	@Length(max = 100)
	private String descriptionPostAnimal;

	@NotNull
	private Size sizePostAnimal;

	@NotNull
	@Length(max = 50)
	private String imagePostAnimal;

	@ApiModelProperty(hidden = true)
	private List<UsurDTO> usurDTOS;

	@ApiModelProperty(hidden = true)
	private List<ContributionDTO> contributionDTOS;

}
