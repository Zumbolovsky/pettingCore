package br.com.guilinssolution.pettingCore.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idContribution"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContributionDTO {

	@NotNull
	private Integer idContribution;

	@ApiModelProperty(hidden = true)
	private PostAnimalDTO postAnimalDTO;

	@ApiModelProperty(hidden = true)
	private PostItemDTO postItemDTO;

	@NotNull
	@ApiModelProperty(hidden = true)
	private UsurDTO usurDTOByIdDonator;

	@NotNull
	@ApiModelProperty(hidden = true)
	private UsurDTO usurDTOByIdRequest;

	@NotNull
	@Length(max = 100)
	private String descriptionContribution;

//	@ApiModelProperty(hidden = true)
//	private List<UsurDTOLite> usurDTOS;

}
