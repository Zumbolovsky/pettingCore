package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsurService extends UserDetailsService {

    ListResultExample<UsurDTO> findAll(UsurDTO dto, PageExample page);
    ListResultExample<UsurDTO> findAllLite(UsurDTO dto, PageExample page);
    UsurDTO findOne(Integer id, Custom custom);
    UsurDTO save(UsurDTO dto);
    UsurDTO update(Integer currentId, UsurDTO dto);
    void delete(Integer id);

}
