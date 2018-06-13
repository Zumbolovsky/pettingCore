package br.com.guilinssolution.pettingCore.model.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	private Integer idContribution;

	private PostAnimalDTO postAnimalDTO;

	private PostItemDTO postItemDTO;

	private UsurDTO usurDTOByIdDonator;

	private UsurDTO usurDTOByIdRequest;

	@NotEmpty(message = "Descrição {empty}")
	@Length(max = 100, message = "{length.hundred}")
	private String descriptionContribution;

}
