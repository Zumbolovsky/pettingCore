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

import java.util.ArrayList;
import java.util.List;

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

//        List<UsurEntityLite> usurEntities = entity.getUsurEntities();
//        List<UsurDTOLite> usurDTOS = UsurAdapter.convertToDTOLite(usurEntities);

        return ContributionDTO.builder()
                .idContribution(entity.getIdContribution())
                .descriptionContribution(entity.getDescriptionContribution())
                .postAnimalDTO(postAnimalDTO)
                .postItemDTO(postItemDTO)
                .usurDTOByIdDonator(usurDTOByIdDonator)
                .usurDTOByIdRequest(usurDTOByIdRequest)
//                .usurDTOS(usurDTOS)
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

//        List<UsurDTOLite> usurDTOS = dto.getUsurDTOS();
//        List<UsurEntityLite> usurEntities = UsurAdapter.convertToEntityLite(usurDTOS);

        return ContributionEntity.builder()
                .idContribution(dto.getIdContribution())
                .descriptionContribution(dto.getDescriptionContribution())
                .postAnimalEntity(postAnimalEntity)
                .postItemEntity(postItemEntity)
                .usurEntityByIdDonator(usurEntityByIdDonator)
                .usurEntityByIdRequest(usurEntityByIdRequest)
//                .usurEntities(usurEntities)
                .build();
    }

    public static List<ContributionDTO> convertToDTOLite(List<ContributionEntity> entities) {
        if(entities == null) {
            return null;
        }
        List<ContributionDTO> dtos = new ArrayList<>();

        for (ContributionEntity entity : entities) {
            ContributionDTO dto = convertToDTOLite(entity);
            if(dto != null) {
                dtos.add(dto);
            }
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

    public static List<ContributionEntity> convertToEntityLite(List<ContributionDTO> dtos) {
        if(dtos == null) {
            return null;
        }
        List<ContributionEntity> entities = new ArrayList<>();

        for (ContributionDTO dto : dtos) {
            ContributionEntity entity = convertToEntityLite(dto);
            if(entity != null) {
                dtos.add(dto);
            }
        }

        return entities;
    }

    public static ContributionEntity convertToEntityLite(ContributionDTO dto) {
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

    public static ContributionEntity convertToEntity(ContributionDTO dto, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToEntityLite(dto);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToEntity(dto);
        }
        return null;
    }

}
