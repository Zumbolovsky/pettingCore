package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;

public interface PostAnimalRepositoryCustom {

    ListResultDTO<PostAnimalDTO> listByUsur(Integer idUsur, PageDTO pageDTO);

}
