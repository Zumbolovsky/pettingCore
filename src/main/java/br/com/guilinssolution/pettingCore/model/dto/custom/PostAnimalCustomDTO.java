package br.com.guilinssolution.pettingCore.model.dto.custom;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idPostAnimal"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostAnimalCustomDTO {

    private Integer idPostAnimal;

    private String titlePostAnimal;

    private String descriptionPostAnimal;

    private Size sizePostAnimal;
    
}
