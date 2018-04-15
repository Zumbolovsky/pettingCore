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
	private Set<ContributionDTO> contributionDTOS;

	@ApiModelProperty(hidden = true)
	private Set<UsurDTO> usurDTOS;

}
