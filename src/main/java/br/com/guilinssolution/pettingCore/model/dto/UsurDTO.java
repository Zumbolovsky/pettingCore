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
public class UsurDTO {

	@NotNull
	private int idUsur;

	@ApiModelProperty(hidden = true)
	private ContributionDTO contributionDTO;

	@ApiModelProperty(hidden = true)
	private PostAnimalDTO postAnimalDTO;

	@ApiModelProperty(hidden = true)
	private PostItemDTO postItemDTO;

	@NotNull
	@Length(max = 30)
	private String nameUsur;

	@NotNull
	@Length(max = 11)
	private String cpfUsur;

	@NotNull
	@Length(max = 30)
	private String addressUsur;

	@NotNull
	@Length(max = 30)
	private String cityUsur;

	@NotNull
	@Length(max = 2)
	private String stateUsur;

	@NotNull
	@Length(max = 15)
	private String cellphoneUsur;

	@NotNull
	@Length(max = 15)
	private String phoneUsur;

	@ApiModelProperty(hidden = true)
	private List<ContributionDTO> contributionsForIdDonator;

	@ApiModelProperty(hidden = true)
	private List<ContributionDTO> contributionsForIdRequest;

}
