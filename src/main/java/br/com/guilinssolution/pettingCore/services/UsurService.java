package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;

public interface UsurService {

    ListResultDTO<UsurDTO> findAll(UsurDTO dto, PageDTO page);
    ListResultDTO<UsurDTO> findAllLite(UsurDTO dto, PageDTO page);
    UsurDTO findOne(Integer id);
    UsurDTO save(UsurDTO dto);
    UsurDTO update(Integer id, UsurDTO dto);
    void delete(Integer id);

}
