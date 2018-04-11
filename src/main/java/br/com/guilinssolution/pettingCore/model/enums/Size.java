package br.com.guilinssolution.pettingCore.model.enums;

public enum Size {

    PEQUENO(0),
    MEDIO(1),
    GRANDE(2);

    public int sizeValue;

    Size(int value) {
        this.sizeValue = value;
    }

    public int getValue() {
        return sizeValue;
    }

}
