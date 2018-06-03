package br.com.guilinssolution.pettingCore.model.dto.util;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public ListResultDTO(Pageable pageable, List<T> content, JPAQuery<T> query) {
        Long totalElements = query.fetchCount();

        this.numberOfElements = content.size();
        this.limit = pageable.getPageSize();

        this.totalPages = (totalElements == 0 || this.limit == 0) ? 1 : totalElements/this.limit;
        this.totalPages = (this.totalPages == 0) ? this.totalPages : 1;

        this.offset = (int) pageable.getOffset();
        this.firstPage = (this.offset == 0);
        this.lastPage = (this.offset == (this.totalPages));
        this.totalElements = totalElements;
        this.content = content;
    }

}