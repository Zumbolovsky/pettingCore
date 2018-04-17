package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsurAdapter {

    public static UsurDTO convertToDTO(UsurEntity entity) {
        if (entity == null) {
            return null;
        }

        ContributionEntity contributionEntity = entity.getContributionEntity();
        ContributionDTO contributionDTO = ContributionAdapter.convertToDTOLite(contributionEntity);

        Set<ContributionEntity> contributionEntitiesForIdDonator = entity.getContributionsForIdDonator();
        List<ContributionDTO> contributionDTOSForIdDonator = ContributionAdapter.convertToDTOLite(contributionEntitiesForIdDonator);

        Set<ContributionEntity> contributionEntitiesForIdRequest = entity.getContributionsForIdRequest();
        List<ContributionDTO> contributionDTOSForIdRequest = ContributionAdapter.convertToDTOLite(contributionEntitiesForIdRequest);

        return UsurDTO.builder()
                .idUsur(entity.getIdUsur())
                .addressUsur(entity.getAddressUsur())
                .cellphoneUsur(entity.getCellphoneUsur())
                .cityUsur(entity.getCityUsur())
                .cpfUsur(entity.getCpfUsur())
                .nameUsur(entity.getNameUsur())
                .phoneUsur(entity.getPhoneUsur())
                .stateUsur(entity.getStateUsur())
                .contributionDTO(contributionDTO)
                .contributionsForIdDonator(contributionDTOSForIdDonator)
                .contributionsForIdRequest(contributionDTOSForIdRequest)
                // TODO: .postAnimalDTO(entity.getPostAnimalEntity())
                // TODO: .postItemDTO(entity.getPostItemEntity())
                .build();
    }

    public static UsurEntity convertToEntity(UsurDTO dto) {
        if (dto == null) {
            return null;
        }

        ContributionDTO contributionDTO = dto.getContributionDTO();
        ContributionEntity contributionEntity = ContributionAdapter.convertToEntity(contributionDTO);

        List<ContributionDTO> contributionDTOSForIdDonator = dto.getContributionsForIdDonator();
        Set<ContributionEntity> contributionEntitiesForIdDonator = new HashSet<>();
        for (ContributionDTO contributionDTOForIdDonator : contributionDTOSForIdDonator) {
            ContributionEntity contributionEntityForIdDonator = ContributionAdapter.convertToEntity(contributionDTOForIdDonator);
            contributionEntitiesForIdDonator.add(contributionEntityForIdDonator);
        }

        List<ContributionDTO> contributionDTOSForIdRequest = dto.getContributionsForIdRequest();
        Set<ContributionEntity> contributionEntitiesForIdRequest = new HashSet<>();
        for (ContributionDTO contributionDTOForIdRequest : contributionDTOSForIdRequest) {
            ContributionEntity contributionEntityForIdRequest = ContributionAdapter.convertToEntity(contributionDTOForIdRequest);
            contributionEntitiesForIdRequest.add(contributionEntityForIdRequest);
        }

        return UsurEntity.builder()
                .idUsur(dto.getIdUsur())
                .addressUsur(dto.getAddressUsur())
                .cellphoneUsur(dto.getCellphoneUsur())
                .cityUsur(dto.getCityUsur())
                .cpfUsur(dto.getCpfUsur())
                .nameUsur(dto.getNameUsur())
                .phoneUsur(dto.getPhoneUsur())
                .stateUsur(dto.getStateUsur())
                .contributionEntity(contributionEntity)
                .contributionsForIdDonator(contributionEntitiesForIdDonator)
                .contributionsForIdRequest(contributionEntitiesForIdRequest)
                // TODO: .postAnimalEntity(dto.getPostAnimalDTO())
                // TODO: .postItemEntity(dto.getPostItemDTO())
                .build();
    }

    public static List<UsurDTO> convertToDTOLite(Set<UsurEntity> entities) {
        List<UsurDTO> dtos = new ArrayList<>();

        for (UsurEntity entity : entities) {
            UsurDTO dto = convertToDTOLite(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    public static UsurDTO convertToDTOLite(UsurEntity entity) {
        if(entity == null) {
            return null;
        }

        return UsurDTO.builder()
                .idUsur(entity.getIdUsur())
                .build();
    }
}
