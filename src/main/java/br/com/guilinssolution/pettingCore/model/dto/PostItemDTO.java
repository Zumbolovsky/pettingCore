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

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idPostItem"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostItemDTO {

	@ApiModelProperty(hidden = true)
	private Integer idPostItem;

	@ApiModelProperty(hidden = true)
	private AnimalDTO animalDTO;

	@Length(max = 30, message = "{length.thirty}")
	private String titlePostItem;

	@Length(max = 100, message = "{length.hundred}")
	private String descriptionPostItem;

	@Length(max = 50, message = "{length.fifty}")
	private String imagePostItem;

	private Type typePostItem;

//	@ApiModelProperty(hidden = true)
//	private List<ContributionDTOLite> contributionDTOS;
//
//	@ApiModelProperty(hidden = true)
//	private List<UsurDTOLite> usurDTOS;

}
