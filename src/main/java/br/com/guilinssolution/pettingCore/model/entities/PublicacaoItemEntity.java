package br.com.guilinssolution.pettingCore.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PublicacaoItemEntity implements Serializable {


    private Integer id;


    private String titulo;


    private String descricao;


    private String imagem;


    private Integer idAnimal;

}
