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


    private String nome;


    private String cpf;


    private String endereco;


    private String estado;


    private String cidade;


    private String telefone;


    private String celular;


    private String imagem;


    private Integer idSolicitante;


    private Integer idDoador;


    private Set<Integer> idContribuicao;


    private Set<Integer> idPublicacaoItem;


    private Set<Integer> idPublicacaoAnimal;

}
