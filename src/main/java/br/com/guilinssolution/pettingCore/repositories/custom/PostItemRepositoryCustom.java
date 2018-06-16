package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.enums.Type;

public interface PostItemRepositoryCustom {

    ListResultDTO<PostItemDTO> listByUsur(Integer idUsur, PageDTO pageDTO);
}
