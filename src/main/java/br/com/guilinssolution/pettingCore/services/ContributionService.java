package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.Kind;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;

public interface ContributionService {

    ListResultExample<ContributionDTO> findAll(ContributionDTO dto, PageExample page);
    ListResultExample<ContributionDTO> findAllLite(ContributionDTO dto, PageExample page);
    ListResultExample<ContributionDTO> listByDonator(Integer idUsur, PageExample pageExample, Kind kind);
    ContributionDTO findOne(Integer id);
    ContributionDTO save(ContributionDTO dto, Integer idPostAnimal, Integer idPostItem, Integer idUsurRequest, Integer idUsurDonator, Custom custom);
    ContributionDTO update(Integer currentId, ContributionDTO dto, Integer idPostAnimal, Integer idPostItem, Integer idUsurRequest, Integer idUsurDonator);
    ContributionDTO quickUpdate(Integer currentId, ContributionDTO dto);
    void delete(Integer id);
}
