package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsurService extends UserDetailsService {

    ListResultDTO<UsurDTO> findAll(UsurDTO dto, PageDTO page);
    ListResultDTO<UsurDTO> findAllLite(UsurDTO dto, PageDTO page);
    UsurDTO findOne(Integer id, Custom custom);
    UsurDTO save(UsurDTO dto);
    UsurDTO update(Integer currentId, UsurDTO dto);
    void delete(Integer id);

}
