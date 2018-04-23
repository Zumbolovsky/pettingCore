package br.com.guilinssolution.pettingCore.model.dto.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ListResultDTO<T> extends PageDTO{

    private List<T> content;

    public ListResultDTO(Page<?> page, List<T> content) {
        firstPage = page.isFirst();
        lastPage = page.isLast();
        totalPages = page.getTotalPages();
        numberOfElements = page.getNumberOfElements();
        totalElements = page.getTotalElements();
        limit = page.getSize();
        offset = page.getNumber();
        this.content = content;
    }

}