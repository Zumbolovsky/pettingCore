package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.*;
import br.com.guilinssolution.pettingCore.model.entities.*;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostItemAdapter {

    public static PostItemDTO convertToDTO(PostItemEntity entity) {
        if(entity == null) {
            return null;
        }

        AnimalEntity animalEntity = entity.getAnimalEntity();
        AnimalDTO animalDTO = AnimalAdapter.convertToDTO(animalEntity);

        Set<ContributionEntity> contributionEntities = entity.getContributionEntities();
        List<ContributionDTO> contributionDTOS = ContributionAdapter.convertToDTOLite(contributionEntities);

        Set<UsurEntity> usurEntities = entity.getUsurEntities();
        List<UsurDTO> usurDTOS = UsurAdapter.convertToDTOLite(usurEntities);

        return PostItemDTO.builder()
                .idPostItem(entity.getIdPostItem())
                .descriptionPostItem(entity.getDescriptionPostItem())
                .imagePostItem(entity.getImagePostItem())
                .titlePostItem(entity.getTitlePostItem())
                .animalDTO(animalDTO)
                .contributionDTOS(contributionDTOS)
                .usurDTOS(usurDTOS)
                .build();
    }

    public static PostItemEntity convertToEntity(PostItemDTO dto) {
        if(dto == null) {
            return null;
        }

        AnimalDTO animalDTO = dto.getAnimalDTO();
        AnimalEntity animalEntity  = AnimalAdapter.convertToEntity(animalDTO);

        List<ContributionDTO> contributionDTOS = dto.getContributionDTOS();
        Set<ContributionEntity> contributionEntities = ContributionAdapter.convertToEntityLite(contributionDTOS);

        List<UsurDTO> usurDTOS = dto.getUsurDTOS();
        Set<UsurEntity> usurEntities = UsurAdapter.convertToEntityLite(usurDTOS);

        return PostItemEntity.builder()
                .idPostItem(dto.getIdPostItem())
                .descriptionPostItem(dto.getDescriptionPostItem())
                .imagePostItem(dto.getImagePostItem())
                .titlePostItem(dto.getTitlePostItem())
                .animalEntity(animalEntity)
                .contributionEntities(contributionEntities)
                .usurEntities(usurEntities)
                .build();
    }

    public static List<PostItemDTO> convertToDTOLite(Set<PostItemEntity> entities) {
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

    public static Set<PostItemEntity> convertToEntityLite(List<PostItemDTO> dtos) {
        if(dtos == null) {
            return null;
        }
        Set<PostItemEntity> entities = new HashSet<>();

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
