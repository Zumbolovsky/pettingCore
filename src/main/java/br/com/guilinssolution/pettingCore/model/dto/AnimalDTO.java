package br.com.guilinssolution.pettingCore.model.dto;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idAnimal"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalDTO {

	@ApiModelProperty(hidden = true)
	private Integer idAnimal;

	private Species speciesAnimal;

	@Length(max = 30, message = "{length.thirty}")
	private String breedAnimal;

}
