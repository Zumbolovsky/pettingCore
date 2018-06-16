package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;

public interface PostItemRepositoryCustom {

    ListResultExample<PostItemDTO> listByUsur(Integer idUsur, PageExample pageExample);
}
