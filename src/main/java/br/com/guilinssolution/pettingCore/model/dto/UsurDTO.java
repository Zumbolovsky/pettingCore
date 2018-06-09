package br.com.guilinssolution.pettingCore.model.dto;

import br.com.guilinssolution.pettingCore.model.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idUsur"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsurDTO {

    @ApiModelProperty(hidden = true)
	private Integer idUsur;

	@NotEmpty(message = "Nome {empty}")
	@Length(max = 30, message = "{length.thirty}")
	private String nameUsur;

	@CPF(message = "{invalid.cpf}")
	@NotEmpty(message = "CPF {empty}")
	@Length(max = 11, message = "{length.cpf}")
	private String cpfUsur;

	@NotEmpty(message = "Endereço {empty}")
	@Length(max = 30, message = "{length.thirty}")
	private String addressUsur;

	@NotEmpty(message = "Cidade {empty}")
	@Length(max = 30, message = "{length.thirty}")
	private String cityUsur;

	@ApiModelProperty(example = "john@doe.com")
	@Email(message = "{invalid.email}")
	@NotEmpty(message = "Email {empty}")
	@Length(max = 30, message = "{length.thirty}")
	private String emailUsur;

	@NotEmpty(message = "Senha {empty}")
	@Length(max = 15, min = 6, message = "{length.password}")
	private String passwordUsur;

	@NotNull(message = "Estado {empty}")
	private State stateUsur;

	@ApiModelProperty(example = "(00) 90000-0000")
	@NotEmpty(message = "Celular {empty}")
	@Length(max = 15, message = "{length.phone}")
	private String cellphoneUsur;

	@ApiModelProperty(example = "(00) 0000-0000")
	@NotEmpty(message = "Telefone {empty}")
	@Length(max = 15, message = "{length.phone}")
	private String phoneUsur;

	//todo: só tirar NotEmpty e colocar ApiModelProperty se tiver imagem Multipart
	//@ApiModelProperty(hidden = true)
//	@NotEmpty(message = "Imagem {empty}")
//	@Length(max = 50, message = "{length.fifty}")
//	private String imageUsur;


}
