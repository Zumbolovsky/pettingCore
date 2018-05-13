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

	@ApiModelProperty(hidden = true)
	private ContributionDTO contributionDTO;

	@ApiModelProperty(hidden = true)
	private PostAnimalDTO postAnimalDTO;

	@ApiModelProperty(hidden = true)
	private PostItemDTO postItemDTO;

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

	private State stateUsur;

	@Length(max = 15, message = "{length.phone}")
	private String cellphoneUsur;

	@Length(max = 15, message = "{length.phone}")
	private String phoneUsur;

//	@ApiModelProperty(hidden = true)
//	private List<ContributionDTOLite> contributionsForIdDonator;
//
//	@ApiModelProperty(hidden = true)
//	private List<ContributionDTOLite> contributionsForIdRequest;

}
