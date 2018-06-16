package br.com.guilinssolution.pettingCore.model.dto.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idContribution"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContributionCustomDTO {

    private Integer idContribution;

    private Integer idPost;

    private String titlePost;

    private String descriptionPost;

    private String kindOfPost;

}
