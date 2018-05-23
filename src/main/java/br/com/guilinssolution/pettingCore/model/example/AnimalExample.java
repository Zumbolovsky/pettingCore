package br.com.guilinssolution.pettingCore.model.example;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalExample {

    private Species speciesAnimal;

    @Length(max = 30, message = "{length.thirty}")
    private String breedAnimal;

}
