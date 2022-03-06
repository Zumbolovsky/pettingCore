package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;

public interface AnimalService {

    ListResultExample<AnimalDTO> findAll(AnimalDTO dto, PageExample page);
    ListResultExample<AnimalDTO> findAllLite(AnimalDTO dto, PageExample page);
    AnimalDTO findOne(Integer id);
    AnimalDTO save(AnimalDTO dto);
    AnimalDTO update(Integer currentId, AnimalDTO dto);
    void delete(Integer id);

}
