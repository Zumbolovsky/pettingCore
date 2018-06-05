package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.example.UsurExample;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UsurService extends UserDetailsService {

    ListResultDTO<UsurDTO> findAll(UsurExample example, PageDTO page);
    ListResultDTO<UsurDTO> findAllLite(UsurExample example, PageDTO page);
    UsurDTO findOne(Integer id);
    UsurDTO save(UsurDTO dto, MultipartFile file);
    UsurDTO update(Integer currentId, UsurDTO dto, MultipartFile file);
    void delete(Integer id);

}
