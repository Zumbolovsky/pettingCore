package br.com.guilinssolution.pettingCore.model.entities;

import br.com.guilinssolution.pettingCore.model.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PublicacaoAnimalEntity implements Serializable {


    private Integer id;


    private String title;


    private Size size;


    private String description;


    private String image;


    private Integer idAnimal;

}
