package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.enums.Type;
import br.com.guilinssolution.pettingCore.model.example.PostItemExample;

public interface PostItemService {

    ListResultDTO<PostItemDTO> findAll(PostItemExample example, Type type, PageDTO page);
    ListResultDTO<PostItemDTO> listByUsur(Integer idUsur, PageDTO pageDTO);
    ListResultDTO<PostItemDTO> findAllLite(PostItemExample example, PageDTO page);
    PostItemDTO findOne(Integer id);
    PostItemDTO save(PostItemExample example, Integer idUsur);
    PostItemDTO update(Integer currentId, PostItemExample example, Integer idUsur);
    PostItemDTO quickUpdate(Integer currentId, PostItemExample example);
    void delete(Integer id);
}
