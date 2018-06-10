package br.com.guilinssolution.pettingCore.model.dto;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idPostAnimal"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostAnimalDTO {

	@ApiModelProperty(hidden = true)
	private Integer idPostAnimal;

	@ApiModelProperty(hidden = true)
	private UsurDTO usurDTO;

	@NotEmpty(message = "Título {empty}")
	@Length(max = 30, message = "{length.thirty}")
	private String titlePostAnimal;

	@NotEmpty(message = "Descrição {empty}")
	@Length(max = 100, message = "{length.hundred}")
	private String descriptionPostAnimal;

	@NotNull(message = "Porte {empty}")
	private Size sizePostAnimal;

	//todo: só tirar NotEmpty e colocar ApiModelProperty se tiver imagem Multipart
	//@ApiModelProperty(hidden = true)
//	@NotEmpty(message = "Imagem {empty}")
//	@Length(max = 50, message = "{length.fifty}")
//	private String imagePostAnimal;

	@NotNull(message = "Espécie {empty}")
	private Species speciesPostAnimal;
}
