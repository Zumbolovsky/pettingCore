package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.example.UsurExample;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsurService extends UserDetailsService {

    ListResultDTO<UsurDTO> findAll(UsurExample example, PageDTO page);
    ListResultDTO<UsurDTO> findAllLite(UsurExample example, PageDTO page);
    UsurDTO findOne(Integer id);
    UsurDTO save(UsurExample example);
    UsurDTO update(Integer currentId, UsurExample example);
    void delete(Integer id);

}
