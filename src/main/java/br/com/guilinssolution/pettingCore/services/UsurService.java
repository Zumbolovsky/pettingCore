package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.example.UsurExample;

public interface UsurService {

    ListResultDTO<UsurDTO> findAll(UsurExample example, PageDTO page);
    ListResultDTO<UsurDTO> findAllLite(UsurExample example, PageDTO page);
    UsurDTO findOne(Integer id);
    UsurDTO save(UsurDTO dto);
    UsurDTO update(Integer currentId, UsurDTO dto);
    void delete(Integer id);

}
