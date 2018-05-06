package br.com.guilinssolution.pettingCore.model.dto;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
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
@EqualsAndHashCode(of = {"idPostAnimal"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostAnimalDTO {

	@NotNull
	private Integer idPostAnimal;

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
	@Length(max = 7)
	private Size sizePostAnimal;

	@NotNull
	@Length(max = 50)
	private String imagePostAnimal;

//	@ApiModelProperty(hidden = true)
//	private List<UsurDTOLite> usurDTOS;
//
//	@ApiModelProperty(hidden = true)
//	private List<ContributionDTOLite> contributionDTOS;

}
