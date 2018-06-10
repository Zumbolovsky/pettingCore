package br.com.guilinssolution.pettingCore.model.dto;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.guilinssolution.pettingCore.model.enums.Type;
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
@EqualsAndHashCode(of = {"idPostItem"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostItemDTO {

	@ApiModelProperty(hidden = true)
	private Integer idPostItem;

	@ApiModelProperty(hidden = true)
	private UsurDTO usurDTO;

	@NotEmpty(message = "Título {empty}")
	@Length(max = 30, message = "{length.thirty}")
	private String titlePostItem;

	@NotEmpty(message = "Descrição {empty}")
	@Length(max = 100, message = "{length.hundred}")
	private String descriptionPostItem;

	//todo: só tirar NotEmpty e colocar ApiModelProperty se tiver imagem Multipart
	//@ApiModelProperty(hidden = true)
//	@NotEmpty(message = "Imagem {empty}")
//	@Length(max = 50, message = "{length.fifty}")
//	private String imagePostItem;

	@NotNull(message = "Tipo {empty}")
	private Type typePostItem;

	@NotNull(message = "Espécie {empty}")
	private Species speciesPostItem;
}
