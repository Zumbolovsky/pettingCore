package br.com.guilinssolution.pettingCore.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idAnimal"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalDTO {

	@NotNull
	private Integer idAnimal;

	@NotNull
	@Length(max = 30)
	private String speciesAnimal;

	@NotNull
	@Length(max = 30)
	private String breedAnimal;

//	@ApiModelProperty(hidden = true)
//	private List<PostAnimalDTOLite> postAnimalDTOS;
//
//	@ApiModelProperty(hidden = true)
//	private List<PostItemDTOLite> postItemDTOS;

}
