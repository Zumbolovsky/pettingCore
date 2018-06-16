package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.Type;

public interface PostItemService {

    ListResultDTO<PostItemDTO> findAll(PostItemDTO dto, Type type, Custom custom, PageDTO page);
    ListResultDTO<PostItemDTO> findAllLite(PostItemDTO dto, PageDTO page);
    ListResultDTO<PostItemDTO> listByUsur(Integer idUsur, PageDTO pageDTO);
    PostItemDTO findOne(Integer id, Custom custom);
    PostItemDTO save(PostItemDTO dto, Integer idUsur);
    PostItemDTO update(Integer currentId, PostItemDTO dto, Integer idUsur);
    PostItemDTO quickUpdate(Integer currentId, PostItemDTO dto);
    void delete(Integer id);
}
