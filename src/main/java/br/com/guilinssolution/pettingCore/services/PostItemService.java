package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;

public interface PostItemService {

    ListResultDTO<PostItemDTO> findAll(PostItemDTO dto, PageDTO page);
    ListResultDTO<PostItemDTO> findAllLite(PostItemDTO dto, PageDTO page);
    PostItemDTO findOne(Integer id);
    PostItemDTO save(PostItemDTO dto);
    PostItemDTO update(Integer id, PostItemDTO dto);
    void delete(Integer id);

}
