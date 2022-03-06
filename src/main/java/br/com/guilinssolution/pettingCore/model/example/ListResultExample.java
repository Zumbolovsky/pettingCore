package br.com.guilinssolution.pettingCore.model.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ListResultExample<T> extends PageExample {

    private List<T> content;

    public ListResultExample(Page<?> page, List<T> content) {
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