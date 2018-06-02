package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.example.ContributionExample;

public interface ContributionService {

    ListResultDTO<ContributionDTO> findAll(ContributionExample example, PageDTO page);
    ListResultDTO<ContributionDTO> findAllLite(ContributionExample example, PageDTO page);
    ContributionDTO findOne(Integer id);
    ContributionDTO save(ContributionDTO dto, Integer idPostAnimal, Integer idPostItem, Integer idUsurRequest, Integer idUsurDonator);
    ContributionDTO update(Integer currentId, ContributionDTO dto, Integer idPostAnimal, Integer idPostItem, Integer idUsurRequest, Integer idUsurDonator);
    ContributionDTO quickUpdate(Integer currentId, ContributionDTO dto);
    void delete(Integer id);
    ListResultDTO<ContributionDTO> listByDonator(Integer idUsur, PageDTO pageDTO);
}
