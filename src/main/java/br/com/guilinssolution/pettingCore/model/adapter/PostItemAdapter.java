package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.*;
import br.com.guilinssolution.pettingCore.model.entities.*;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

import java.util.ArrayList;
import java.util.List;

public class PostItemAdapter {

    public static PostItemDTO convertToDTO(PostItemEntity entity) {
        if(entity == null) {
            return null;
        }

        AnimalEntity animalEntity = entity.getAnimalEntity();
        AnimalDTO animalDTO = AnimalAdapter.convertToDTO(animalEntity);

//        List<ContributionEntityLite> contributionEntities = entity.getContributionEntities();
//        List<ContributionDTOLite> contributionDTOS = ContributionAdapter.convertToDTOLite(contributionEntities);
//
//        List<UsurEntityLite> usurEntities = entity.getUsurEntities();
//        List<UsurDTOLite> usurDTOS = UsurAdapter.convertToDTOLite(usurEntities);

        return PostItemDTO.builder()
                .idPostItem(entity.getIdPostItem())
                .descriptionPostItem(entity.getDescriptionPostItem())
                .imagePostItem(entity.getImagePostItem())
                .titlePostItem(entity.getTitlePostItem())
                .typePostItem(entity.getTypePostItem())
                .animalDTO(animalDTO)
//                .contributionDTOS(contributionDTOS)
//                .usurDTOS(usurDTOS)
                .build();
    }

    public static PostItemEntity convertToEntity(PostItemDTO dto) {
        if(dto == null) {
            return null;
        }

        AnimalDTO animalDTO = dto.getAnimalDTO();
        AnimalEntity animalEntity  = AnimalAdapter.convertToEntity(animalDTO);

//        List<ContributionDTOLite> contributionDTOS = dto.getContributionDTOS();
//        List<ContributionEntityLite> contributionEntities = ContributionAdapter.convertToEntityLite(contributionDTOS);
//
//        List<UsurDTOLite> usurDTOS = dto.getUsurDTOS();
//        List<UsurEntityLite> usurEntities = UsurAdapter.convertToEntityLite(usurDTOS);

        return PostItemEntity.builder()
                .idPostItem(dto.getIdPostItem())
                .descriptionPostItem(dto.getDescriptionPostItem())
                .imagePostItem(dto.getImagePostItem())
                .titlePostItem(dto.getTitlePostItem())
                .typePostItem(dto.getTypePostItem())
                .animalEntity(animalEntity)
//                .contributionEntities(contributionEntities)
//                .usurEntities(usurEntities)
                .build();
    }

    public static List<PostItemDTO> convertToDTOLite(List<PostItemEntity> entities) {
        if(entities == null) {
            return null;
        }
        List<PostItemDTO> dtos = new ArrayList<>();

        for (PostItemEntity entity : entities) {
            PostItemDTO dto = convertToDTOLite(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }

        return dtos;
    }

    public static PostItemDTO convertToDTOLite(PostItemEntity entity) {
        if(entity == null) {
            return null;
        }

        return PostItemDTO.builder()
                .idPostItem(entity.getIdPostItem())
                .build();
    }

    public static List<PostItemEntity> convertToEntityLite(List<PostItemDTO> dtos) {
        if(dtos == null) {
            return null;
        }
        List<PostItemEntity> entities = new ArrayList<>();

        for (PostItemDTO dto : dtos) {
            PostItemEntity entity = convertToEntityLite(dto);
            if(entity != null) {
                dtos.add(dto);
            }
        }

        return entities;
    }

    public static PostItemEntity convertToEntityLite(PostItemDTO dto) {
        if(dto == null) {
            return null;
        }

        return PostItemEntity.builder()
                .idPostItem(dto.getIdPostItem())
                .build();
    }

    public static PostItemDTO convertToDTO(PostItemEntity entity, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToDTOLite(entity);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToDTO(entity);
        }
        return null;
    }

    public static PostItemEntity convertToEntity(PostItemDTO dto, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToEntityLite(dto);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToEntity(dto);
        }
        return null;
    }

}
