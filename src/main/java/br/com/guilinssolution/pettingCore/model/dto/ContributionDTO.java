package br.com.guilinssolution.pettingCore.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ContributionDTO {

	@NotNull
	private int idContribution;

	@ApiModelProperty(hidden = true)
	private PostAnimalDTO postAnimalDTO;

	@ApiModelProperty(hidden = true)
	private PostItemDTO postItemDTO;

	@NotNull
	private UsurDTO usurDTOByIdDonator;

	@NotNull
	private UsurDTO usurDTOByIdRequest;

	@NotNull
	@Length(max = 100)
	private String descriptionContribution;

	@ApiModelProperty(hidden = true)
	private Set<UsurDTO> usurDTOS;

}
