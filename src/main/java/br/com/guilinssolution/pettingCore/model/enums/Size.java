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

    public Integer getValue() {
        return sizeValue;
    }

    public static Size getEnum(Integer id) {
        if(id != null) {
            Size[] values = Size.values();
            for (Size type : values) {
                if(type.getSizeValue().equals(id)) {
                    return type;
                }
            }
        }
        return null;
    }

}
