package br.com.guilinssolution.pettingCore.model.dto.custom;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.model.enums.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idPostItem"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostItemCustomDTO {

    private Integer idPostItem;

    private String titlePostAnimal;

    private String descriptionPostAnimal;

    private Type typePostAnimal;

    private Species speciesPostAnimal;

    private Integer idUsur;

    private String nameUsur;

}
