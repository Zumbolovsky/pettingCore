package br.com.guilinssolution.pettingCore.model.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idContribution"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContributionDTO {

	@ApiModelProperty(hidden = true)
	private Integer idContribution;

	@ApiModelProperty(hidden = true)
	private PostAnimalDTO postAnimalDTO;

	@ApiModelProperty(hidden = true)
	private PostItemDTO postItemDTO;

	@ApiModelProperty(hidden = true)
	private UsurDTO usurDTOByIdDonator;

	@ApiModelProperty(hidden = true)
	private UsurDTO usurDTOByIdRequest;

	@NotEmpty(message = "Descrição {empty}")
	@Length(max = 100, message = "{length.hundred}")
	private String descriptionContribution;

}
