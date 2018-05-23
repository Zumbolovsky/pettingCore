package br.com.guilinssolution.pettingCore.model.example;

import br.com.guilinssolution.pettingCore.model.enums.Type;
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
public class PostItemExample {

    @Length(max = 30, message = "{length.thirty}")
    private String titlePostItem;

    @Length(max = 100, message = "{length.hundred}")
    private String descriptionPostItem;

    @Length(max = 50, message = "{length.fifty}")
    private String imagePostItem;

    private Type typePostItem;

}
