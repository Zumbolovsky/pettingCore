package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.enums.Kind;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;

public interface ContributionRepositoryCustom {

    ListResultExample<ContributionDTO> listByDonator(Integer idUsur, PageExample pageExample, Kind kind);

}
