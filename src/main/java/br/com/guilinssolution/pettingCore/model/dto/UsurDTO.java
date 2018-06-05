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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idUsur"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsurDTO {

    @ApiModelProperty(hidden = true)
	private Integer idUsur;

	@Length(max = 30, message = "{length.thirty}")
	private String nameUsur;

	@Length(max = 11, message = "{length.cpf}")
	private String cpfUsur;

	@Length(max = 30, message = "{length.thirty}")
	private String addressUsur;

	@Length(max = 30, message = "{length.thirty}")
	private String cityUsur;

	@Length(max = 30, message = "{length.thirty}")
	private String emailUsur;

	@Length(max = 15, min = 6, message = "{length.password}")
	private String passwordUsur;

	private State stateUsur;

	@Length(max = 15, message = "{length.phone}")
	private String cellphoneUsur;

	@Length(max = 15, message = "{length.phone}")
	private String phoneUsur;

	@Length(max = 50, message = "{length.fifty}")
	private String imageUsur;


}
