package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

public class ContributionAdapter {

    public static ContributionDTO convertToDTO(ContributionEntity entity) {
        if(entity == null) {
            return null;
        }

        PostAnimalEntity postAnimalEntity = entity.getPostAnimalEntity();
        PostAnimalDTO postAnimalDTO = PostAnimalAdapter.convertToDTO(postAnimalEntity);

        PostItemEntity postItemEntity = entity.getPostItemEntity();
        PostItemDTO postItemDTO = PostItemAdapter.convertToDTO(postItemEntity);

        UsurEntity usurEntityByIdDonator = entity.getUsurEntityByIdDonator();
        UsurDTO usurDTOByIdDonator = UsurAdapter.convertToDTO(usurEntityByIdDonator);

        UsurEntity usurEntityByIdRequest = entity.getUsurEntityByIdRequest();
        UsurDTO usurDTOByIdRequest = UsurAdapter.convertToDTO(usurEntityByIdRequest);

        return ContributionDTO.builder()
                .idContribution(entity.getIdContribution())
                .descriptionContribution(entity.getDescriptionContribution())
                .postAnimalDTO(postAnimalDTO)
                .postItemDTO(postItemDTO)
                .usurDTOByIdDonator(usurDTOByIdDonator)
                .usurDTOByIdRequest(usurDTOByIdRequest)
                .build();
    }

    public static ContributionEntity convertToEntity(ContributionDTO dto) {
        if(dto == null) {
            return null;
        }

        PostAnimalDTO postAnimalDTO = dto.getPostAnimalDTO();
        PostAnimalEntity postAnimalEntity = PostAnimalAdapter.convertToEntity(postAnimalDTO);

        PostItemDTO postItemDTO = dto.getPostItemDTO();
        PostItemEntity postItemEntity = PostItemAdapter.convertToEntity(postItemDTO);

        UsurDTO usurDTOByIdDonator = dto.getUsurDTOByIdDonator();
        UsurEntity usurEntityByIdDonator = UsurAdapter.convertToEntity(usurDTOByIdDonator);

        UsurDTO usurDTOByIdRequest = dto.getUsurDTOByIdRequest();
        UsurEntity usurEntityByIdRequest = UsurAdapter.convertToEntity(usurDTOByIdRequest);

        return ContributionEntity.builder()
                .idContribution(dto.getIdContribution())
                .descriptionContribution(dto.getDescriptionContribution())
                .postAnimalEntity(postAnimalEntity)
                .postItemEntity(postItemEntity)
                .usurEntityByIdDonator(usurEntityByIdDonator)
                .usurEntityByIdRequest(usurEntityByIdRequest)
                .build();
    }


    private static ContributionDTO convertToDTOLite(ContributionEntity entity) {
        if(entity == null) {
            return null;
        }

        return ContributionDTO.builder()
                .idContribution(entity.getIdContribution())
                .build();
    }

    private static ContributionEntity convertToEntityLite(ContributionDTO dto) {
        if(dto == null) {
            return null;
        }

        return ContributionEntity.builder()
                .idContribution(dto.getIdContribution())
                .build();
    }

    public static ContributionDTO convertToDTO(ContributionEntity entity, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToDTOLite(entity);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToDTO(entity);
        }
        return null;
    }

}
