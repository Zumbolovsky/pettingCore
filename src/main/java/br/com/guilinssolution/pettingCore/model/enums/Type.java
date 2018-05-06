package br.com.guilinssolution.pettingCore.model.enums;

import lombok.Getter;

public enum Type {

    REMEDIO(0),
    PRODUTO(1);

    @Getter
    public Integer typeValue;

    Type(Integer value) {
        this.typeValue = value;
    }

}
