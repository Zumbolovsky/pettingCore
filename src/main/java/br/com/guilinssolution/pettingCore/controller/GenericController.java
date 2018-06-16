package br.com.guilinssolution.pettingCore.controller;

import br.com.guilinssolution.pettingCore.model.dto.util.PageableDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GenericController {

    PageableDTO buildPageableDTO(ListResultExample listResultExample, List content) {
        return new PageableDTO(listResultExample.isFirstPage(), listResultExample.isLastPage(),
            listResultExample.getTotalPages(), listResultExample.getNumberOfElements(), listResultExample.getTotalElements(),
            listResultExample.getLimit(), listResultExample.getOffset(), content);
    }

}
