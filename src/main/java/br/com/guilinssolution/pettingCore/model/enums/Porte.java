package br.com.guilinssolution.pettingCore.model.enums;

public enum Porte {

    PEQUENO(0),
    MEDIO(1),
    GRANDE(2);

    public int valorPorte;

    Porte(int valor) {
        this.valorPorte = valor;
    }

    public int getValor() {
        return valorPorte;
    }

}
