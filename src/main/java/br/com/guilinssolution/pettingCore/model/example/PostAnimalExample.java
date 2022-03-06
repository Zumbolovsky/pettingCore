package br.com.guilinssolution.pettingCore.model.example;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostAnimalExample {

    @Length(max = 30, message = "{length.thirty}")
    private String titlePostAnimal;

    @Length(max = 100, message = "{length.hundred}")
    private String descriptionPostAnimal;

    private Size sizePostAnimal;

//    @Length(max = 50, message = "{length.fifty}")
//    private String imagePostAnimal;

    private Species speciesPostAnimal;
}
