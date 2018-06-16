package br.com.guilinssolution.pettingCore.model.dto.custom;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idPostItem"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostItemCustomDTOForList {

    private Integer idPostItem;

    private String titlePostItem;

    private String descriptionPostItem;

    private Species speciesPostItem;

}
