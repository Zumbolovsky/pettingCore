package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.Species;

public interface PostAnimalService {

    ListResultExample<PostAnimalDTO> findAll(PostAnimalDTO dto, Species species, Custom custom, PageExample page);
    ListResultExample<PostAnimalDTO> findAllLite(PostAnimalDTO dto, PageExample page);
    ListResultExample<PostAnimalDTO> listByUsur(Integer idUsur, PageExample pageExample);
    PostAnimalDTO findOne(Integer id, Custom custom);
    PostAnimalDTO save(PostAnimalDTO dto, Integer idUsur);
    PostAnimalDTO update(Integer currentId, PostAnimalDTO dto, Integer idUsur);
    PostAnimalDTO quickUpdate(Integer currentId, PostAnimalDTO dto);
    void delete(Integer id);

}
