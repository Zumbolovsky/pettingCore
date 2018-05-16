package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;

public interface AnimalService {

    ListResultDTO<AnimalDTO> findAll(AnimalDTO dto, PageDTO page);
    ListResultDTO<AnimalDTO> findAllLite(AnimalDTO dto, PageDTO page);
    AnimalDTO findOne(Integer id);
    AnimalDTO save(AnimalDTO dto);
    AnimalDTO update(Integer currentId, AnimalDTO dto);
    void delete(Integer id);

}
