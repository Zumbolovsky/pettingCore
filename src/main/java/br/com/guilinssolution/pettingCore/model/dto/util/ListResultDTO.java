package br.com.guilinssolution.pettingCore.model.dto.util;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ListResultDTO<T> extends PageDTO {

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

    public ListResultDTO(Pageable pageable, List<T> content, JPAQuery<T> query) {
        Long totalElements = query.fetchCount();

        numberOfElements = content.size();
        limit = pageable.getPageSize();

        totalPages = (totalElements == 0 || limit == 0) ? 1 : totalElements/limit;
        totalPages = (totalPages == 0) ? totalPages : 1;

        offset = (int) pageable.getOffset();
        firstPage = (offset == 0);
        lastPage = (offset == (totalPages));
        this.totalElements = totalElements;
        this.content = content;
    }

}