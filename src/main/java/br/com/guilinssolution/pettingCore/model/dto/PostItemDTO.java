package br.com.guilinssolution.pettingCore.model.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.guilinssolution.pettingCore.model.enums.Type;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

	@ApiModelProperty(hidden = true)
	private UsurDTO usurDTO;

	@Length(max = 30, message = "{length.thirty}")
	private String titlePostItem;

	@Length(max = 100, message = "{length.hundred}")
	private String descriptionPostItem;

	@Length(max = 50, message = "{length.fifty}")
	private String imagePostItem;

	private Type typePostItem;

}
