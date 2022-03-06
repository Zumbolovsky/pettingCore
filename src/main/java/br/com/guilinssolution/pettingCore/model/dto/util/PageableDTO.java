package br.com.guilinssolution.pettingCore.model.dto.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageableDTO<T> {

    private final boolean firstPage;
    private final boolean lastPage;
    private final long totalPages;
    private final int numberOfElements;
    private final long totalElements;
    private final int limit;
    private final int offset;
    private final List<T> content;

}
