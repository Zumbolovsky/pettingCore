package br.com.guilinssolution.pettingCore.model.dto;

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
@EqualsAndHashCode(of = {"idAnimal"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDTO {

	@NotNull
	private Integer idAnimal;

	@NotNull
	@Length(max = 30)
	private String speciesAnimal;

	@NotNull
	@Length(max = 30)
	private String breedAnimal;

	@ApiModelProperty(hidden = true)
	private List<PostAnimalDTO> postAnimalDTOS;

	@ApiModelProperty(hidden = true)
	private List<PostItemDTO> postItemDTOS;

}
