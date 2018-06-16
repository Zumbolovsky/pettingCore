package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.Type;

public interface PostItemService {

    ListResultExample<PostItemDTO> findAll(PostItemDTO dto, Type type, Custom custom, PageExample page);
    ListResultExample<PostItemDTO> findAllLite(PostItemDTO dto, PageExample page);
    ListResultExample<PostItemDTO> listByUsur(Integer idUsur, PageExample pageExample);
    PostItemDTO findOne(Integer id, Custom custom);
    PostItemDTO save(PostItemDTO dto, Integer idUsur);
    PostItemDTO update(Integer currentId, PostItemDTO dto, Integer idUsur);
    PostItemDTO quickUpdate(Integer currentId, PostItemDTO dto);
    void delete(Integer id);
}
