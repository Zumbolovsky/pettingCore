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
public class ContribuicaoEntity implements Serializable {


    private Integer id;


    private String description;


    private Integer idRequest;


    private Integer idDonator;


    private Integer idPostAnimal;


    private Integer idPostItem;

}
