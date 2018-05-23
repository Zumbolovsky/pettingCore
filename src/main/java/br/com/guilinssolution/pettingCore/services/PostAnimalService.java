package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;

public interface PostAnimalService {

    ListResultDTO<PostAnimalDTO> findAll(PostAnimalDTO dto, PageDTO page);
    ListResultDTO<PostAnimalDTO> findAllLite(PostAnimalDTO dto, PageDTO page);
    PostAnimalDTO findOne(Integer id);
    PostAnimalDTO save(PostAnimalDTO dto, Integer idAnimal, Integer idUsur);
    PostAnimalDTO update(Integer currentId, PostAnimalDTO dto, Integer idAnimal, Integer idUsur);
    PostAnimalDTO quickUpdate(Integer currentId, PostAnimalDTO dto);
    void delete(Integer id);

}
