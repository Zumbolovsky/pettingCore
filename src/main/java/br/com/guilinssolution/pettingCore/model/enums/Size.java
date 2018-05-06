package br.com.guilinssolution.pettingCore.model.enums;

import lombok.Getter;

public enum Size {

    PEQUENO(0),
    MEDIO(1),
    GRANDE(2);

    @Getter
    public Integer sizeValue;

    Size(Integer value) {
        this.sizeValue = value;
    }

}
