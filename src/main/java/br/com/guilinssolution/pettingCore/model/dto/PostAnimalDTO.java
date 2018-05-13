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

	@ApiModelProperty(hidden = true)
	private Integer idPostAnimal;

	@ApiModelProperty(hidden = true)
	private AnimalDTO animalDTO;

	@Length(max = 30, message = "{length.thirty}")
	private String titlePostAnimal;

	@Length(max = 100, message = "{length.hundred}")
	private String descriptionPostAnimal;

	private Size sizePostAnimal;

	@Length(max = 50, message = "{length.fifty}")
	private String imagePostAnimal;

//	@ApiModelProperty(hidden = true)
//	private List<UsurDTOLite> usurDTOS;
//
//	@ApiModelProperty(hidden = true)
//	private List<ContributionDTOLite> contributionDTOS;

}
