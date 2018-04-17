package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContributionAdapter {

    public static ContributionDTO convertToDTO(ContributionEntity entity) {
        if(entity == null) {
            return null;
        }

        UsurEntity usurEntityByIdDonator = entity.getUsurEntityByIdDonator();
        UsurDTO usurDTOByIdDonator = UsurAdapter.convertToDTOLite(usurEntityByIdDonator);

        UsurEntity usurEntityByIdRequest = entity.getUsurEntityByIdRequest();
        UsurDTO usurDTOByIdRequest = UsurAdapter.convertToDTOLite(usurEntityByIdRequest);

        Set<UsurEntity> usurEntities = entity.getUsurEntities();
        List<UsurDTO> usurDTOS = UsurAdapter.convertToDTOLite(usurEntities);

        return ContributionDTO.builder()
                .idContribution(entity.getIdContribution())
                .descriptionContribution(entity.getDescriptionContribution())
                // TODO: .postAnimalDTO(entity.getPostAnimalEntity())
                // TODO: .postItemDTO(entity.getPostItemEntity())
                .usurDTOByIdDonator(usurDTOByIdDonator)
                .usurDTOByIdRequest(usurDTOByIdRequest)
                .usurDTOS(usurDTOS)
                .build();
    }

    public static ContributionEntity convertToEntity(ContributionDTO dto) {
        if(dto == null) {
            return null;
        }

        UsurDTO usurDTOByIdDonator = dto.getUsurDTOByIdDonator();
        UsurEntity usurEntityByIdDonator = UsurAdapter.convertToEntity(usurDTOByIdDonator);

        UsurDTO usurDTOByIdRequest = dto.getUsurDTOByIdRequest();
        UsurEntity usurEntityByIdRequest = UsurAdapter.convertToEntity(usurDTOByIdRequest);

        List<UsurDTO> usurDTOS = dto.getUsurDTOS();
        Set<UsurEntity> usurEntities = new HashSet<>();
        for (UsurDTO usurDTO : usurDTOS) {
            UsurEntity usurEntity = UsurAdapter.convertToEntity(usurDTO);
            usurEntities.add(usurEntity);
        }

        return ContributionEntity.builder()
                .idContribution(dto.getIdContribution())
                .descriptionContribution(dto.getDescriptionContribution())
                // TODO: .postAnimalEntity(dto.getPostAnimalDTO())
                // TODO: .postItemEntity(dto.getPostItemDTO())
                .usurEntityByIdDonator(usurEntityByIdDonator)
                .usurEntityByIdRequest(usurEntityByIdRequest)
                .usurEntities(usurEntities)
                .build();
    }

    public static List<ContributionDTO> convertToDTOLite(Set<ContributionEntity> entities) {
        List<ContributionDTO> dtos = new ArrayList<>();

        for (ContributionEntity entity : entities) {
            ContributionDTO dto = convertToDTOLite(entity);
            dtos.add(dto);
        }

        return dtos;
    }

    public static ContributionDTO convertToDTOLite(ContributionEntity entity) {
        if(entity == null) {
            return null;
        }

        return ContributionDTO.builder()
                .idContribution(entity.getIdContribution())
                .build();
    }

}
