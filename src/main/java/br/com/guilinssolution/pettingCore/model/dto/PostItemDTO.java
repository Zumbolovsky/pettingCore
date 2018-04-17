package br.com.guilinssolution.pettingCore.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PostItemDTO {

	@NotNull
	private int idPostItem;

	@NotNull
	@ApiModelProperty(hidden = true)
	private AnimalDTO animalDTO;

	@NotNull
	@Length(max = 30)
	private String titlePostItem;

	@NotNull
	@Length(max = 100)
	private String descriptionPostItem;

	@NotNull
	@Length(max = 50)
	private String imagePostItem;

	@ApiModelProperty(hidden = true)
	private List<ContributionDTO> contributionDTOS;

	@ApiModelProperty(hidden = true)
	private List<UsurDTO> usurDTOS;

}
