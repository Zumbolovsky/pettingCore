package br.com.guilinssolution.pettingCore.model.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idAnimal"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalDTO {

	@ApiModelProperty(hidden = true)
	private Integer idAnimal;

	@NotNull(message = "Espécie {empty}")
	private Species speciesAnimal;

	@NotEmpty(message = "Raça {empty}")
	@Length(max = 30, message = "{length.thirty}")
	private String breedAnimal;

}
