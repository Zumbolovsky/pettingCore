package br.com.guilinssolution.pettingCore.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity implements Serializable {


    private Integer id;


    private String name;


    private String cpf;


    private String address;


    private String state;


    private String city;


    private String phone;


    private String cellphone;


    private String image;


    private Set<Integer> idContribution;


    private Set<Integer> idPostItem;


    private Set<Integer> idPostAnimal;

}
