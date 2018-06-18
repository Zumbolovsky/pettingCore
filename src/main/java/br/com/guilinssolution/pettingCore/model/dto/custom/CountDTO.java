package br.com.guilinssolution.pettingCore.model.dto.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountDTO {

    private Integer cahorro;

    private Integer gato;

    private Integer passaro;

    private Integer roedor;

    private Integer outros;

}
