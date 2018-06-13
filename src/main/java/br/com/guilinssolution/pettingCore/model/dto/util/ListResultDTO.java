package br.com.guilinssolution.pettingCore.model.dto.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ListResultDTO<T> extends PageDTO {

    private List<T> content;

    public ListResultDTO(Page<?> page, List<T> content) {
        this.firstPage = page.isFirst();
        this.lastPage = page.isLast();
        this.totalPages = page.getTotalPages();
        this.numberOfElements = page.getNumberOfElements();
        this.totalElements = page.getTotalElements();
        this.limit = page.getSize();
        this.offset = page.getNumber();
        this.content = content;
    }

}