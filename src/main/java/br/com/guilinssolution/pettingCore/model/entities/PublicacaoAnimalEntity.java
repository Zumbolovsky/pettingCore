package br.com.guilinssolution.pettingCore.model.entities;

import br.com.guilinssolution.pettingCore.model.enums.Porte;
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


    private String titulo;


    private Porte porte;


    private String descricao;


    private String imagem;


    private Integer idAnimal;

}
