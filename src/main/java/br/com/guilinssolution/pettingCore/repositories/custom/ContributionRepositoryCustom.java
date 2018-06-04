package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;

public interface ContributionRepositoryCustom {

    ListResultDTO<ContributionDTO> listByDonator(Integer idUsur, PageDTO pageDTO);
    boolean existsByEntity(ContributionEntity entity);

}
