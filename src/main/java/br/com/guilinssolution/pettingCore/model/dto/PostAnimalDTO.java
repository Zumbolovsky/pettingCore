package br.com.guilinssolution.pettingCore.model.dto;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idPostAnimal"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	private Size sizePostAnimal;

	@NotNull
	@Length(max = 50)
	private String imagePostAnimal;

	@ApiModelProperty(hidden = true)
	private List<UsurDTO> usurDTOS;

	@ApiModelProperty(hidden = true)
	private List<ContributionDTO> contributionDTOS;

}
