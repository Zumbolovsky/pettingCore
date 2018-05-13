package br.com.guilinssolution.pettingCore.model.enums;

import lombok.Getter;

public enum Species {

    CACHORRO(0),
    GATO(1),
    PASSARO(2),
    ROEDOR(3),
    OUTROS(4);

    @Getter
    public Integer speciesValue;

    Species(Integer value) {
        this.speciesValue = value;
    }

}
