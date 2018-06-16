package br.com.guilinssolution.pettingCore.model.dto.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsurCustomDTO {

    private String nameUsur;

    private String emailUsur;

    private String phoneUsur;

}
