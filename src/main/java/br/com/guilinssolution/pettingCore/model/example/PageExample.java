package br.com.guilinssolution.pettingCore.model.example;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageExample {

    @ApiModelProperty(hidden= true)
    boolean firstPage;

    @ApiModelProperty(hidden= true)
    boolean lastPage;

    @ApiModelProperty(hidden= true)
    long totalPages;

    int limit;

    int offset;

    @ApiModelProperty(hidden= true)
    int numberOfElements;

    @ApiModelProperty(hidden= true)
    long totalElements;

}
