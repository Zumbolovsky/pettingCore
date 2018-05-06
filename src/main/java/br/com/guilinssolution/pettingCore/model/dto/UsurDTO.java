package br.com.guilinssolution.pettingCore.model.dto;

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
@EqualsAndHashCode(of = {"idUsur"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsurDTO {

	@NotNull
	private Integer idUsur;

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

//	@ApiModelProperty(hidden = true)
//	private List<ContributionDTOLite> contributionsForIdDonator;
//
//	@ApiModelProperty(hidden = true)
//	private List<ContributionDTOLite> contributionsForIdRequest;

}
