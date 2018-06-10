package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.model.example.PostAnimalExample;

public interface PostAnimalService {

    ListResultDTO<PostAnimalDTO> findAll(PostAnimalExample example, Species species, PageDTO page);
    ListResultDTO<PostAnimalDTO> listByUsur(Integer idUsur, PageDTO pageDTO);
    ListResultDTO<PostAnimalDTO> findAllLite(PostAnimalExample example, PageDTO page);
    PostAnimalDTO findOne(Integer id);
    PostAnimalDTO save(PostAnimalDTO dto, Integer idUsur);
    PostAnimalDTO update(Integer currentId, PostAnimalDTO dto, Integer idUsur);
    PostAnimalDTO quickUpdate(Integer currentId, PostAnimalDTO dto);
    void delete(Integer id);

}
