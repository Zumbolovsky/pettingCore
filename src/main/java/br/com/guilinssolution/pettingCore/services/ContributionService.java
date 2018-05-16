package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;

public interface ContributionService {

    ListResultDTO<ContributionDTO> findAll(ContributionDTO dto, PageDTO page);
    ListResultDTO<ContributionDTO> findAllLite(ContributionDTO dto, PageDTO page);
    ContributionDTO findOne(Integer id);
    ContributionDTO save(ContributionDTO dto, Integer idPostAnimal, Integer idPostItem, Integer idUsurRequest, Integer idUsurDonator);
    ContributionDTO update(Integer currentId, ContributionDTO dto, Integer idPostAnimal, Integer idPostItem, Integer idUsurRequest, Integer idUsurDonator);
    void delete(Integer id);

}
