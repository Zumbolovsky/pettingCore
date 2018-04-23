package br.com.guilinssolution.pettingCore.model.dto.util;

import java.util.List;

import lombok.Data;

@Data
public class ExceptionDTO {
    
    private int code;
    private List<String> description;
    
}
