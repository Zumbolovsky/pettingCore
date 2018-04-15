package br.com.guilinssolution.pettingCore.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {

	@NotNull
	private int idAnimal;

	@NotNull
	@Length(max = 30)
	private String speciesAnimal;

	@NotNull
	@Length(max = 30)
	private String breedAnimal;

	@ApiModelProperty(hidden = true)
	private Set<PostAnimalDTO> postAnimalDTOS;

	@ApiModelProperty(hidden = true)
	private Set<PostItemDTO> postItemDTOS;

}
