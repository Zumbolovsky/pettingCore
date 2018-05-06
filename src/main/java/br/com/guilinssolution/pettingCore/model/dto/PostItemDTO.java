package br.com.guilinssolution.pettingCore.model.dto;

import br.com.guilinssolution.pettingCore.model.enums.Type;
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
@EqualsAndHashCode(of = {"idPostItem"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostItemDTO {

	@NotNull
	private Integer idPostItem;

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

	@NotNull
	@Length(max = 7)
	private Type typePostItem;

//	@ApiModelProperty(hidden = true)
//	private List<ContributionDTOLite> contributionDTOS;
//
//	@ApiModelProperty(hidden = true)
//	private List<UsurDTOLite> usurDTOS;

}
