package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostAnimalAdapter {

    public static PostAnimalDTO convertToDTO(PostAnimalEntity entity) {
        if(entity == null) {
            return null;
        }

        AnimalEntity animalEntity = entity.getAnimalEntity();
        AnimalDTO animalDTO = AnimalAdapter.convertToDTO(animalEntity);

        Set<ContributionEntity> contributionEntities = entity.getContributionEntities();
        List<ContributionDTO> contributionDTOS = ContributionAdapter.convertToDTOLite(contributionEntities);

        Set<UsurEntity> usurEntities = entity.getUsurEntities();
        List<UsurDTO> usurDTOS = UsurAdapter.convertToDTOLite(usurEntities);

        return PostAnimalDTO.builder()
                .idPostAnimal(entity.getIdPostAnimal())
                .descriptionPostAnimal(entity.getDescriptionPostAnimal())
                .imagePostAnimal(entity.getImagePostAnimal())
                .sizePostAnimal(Size.getEnum(entity.getSizePostAnimal()))
                .titlePostAnimal(entity.getTitlePostAnimal())
                .animalDTO(animalDTO)
                .contributionDTOS(contributionDTOS)
                .usurDTOS(usurDTOS)
                .build();
    }

    public static PostAnimalEntity convertToEntity(PostAnimalDTO dto) {
        if(dto == null) {
            return null;
        }

        AnimalDTO animalDTO = dto.getAnimalDTO();
        AnimalEntity animalEntity  = AnimalAdapter.convertToEntity(animalDTO);

        List<ContributionDTO> contributionDTOS = dto.getContributionDTOS();
        Set<ContributionEntity> contributionEntities = ContributionAdapter.convertToEntityLite(contributionDTOS);

        List<UsurDTO> usurDTOS = dto.getUsurDTOS();
        Set<UsurEntity> usurEntities = UsurAdapter.convertToEntityLite(usurDTOS);

        return PostAnimalEntity.builder()
                .idPostAnimal(dto.getIdPostAnimal())
                .descriptionPostAnimal(dto.getDescriptionPostAnimal())
                .imagePostAnimal(dto.getImagePostAnimal())
                .sizePostAnimal(dto.getSizePostAnimal().getSizeValue())
                .titlePostAnimal(dto.getTitlePostAnimal())
                .animalEntity(animalEntity)
                .contributionEntities(contributionEntities)
                .usurEntities(usurEntities)
                .build();
    }

    public static List<PostAnimalDTO> convertToDTOLite(Set<PostAnimalEntity> entities) {
        if(entities == null) {
            return null;
        }
        List<PostAnimalDTO> dtos = new ArrayList<>();

        for (PostAnimalEntity entity : entities) {
            PostAnimalDTO dto = convertToDTOLite(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }

        return dtos;
    }

    public static PostAnimalDTO convertToDTOLite(PostAnimalEntity entity) {
        if(entity == null) {
            return null;
        }

        return PostAnimalDTO.builder()
                .idPostAnimal(entity.getIdPostAnimal())
                .build();
    }

    public static Set<PostAnimalEntity> convertToEntityLite(List<PostAnimalDTO> dtos) {
        if(dtos == null) {
            return null;
        }
        Set<PostAnimalEntity> entities = new HashSet<>();

        for (PostAnimalDTO dto : dtos) {
            PostAnimalEntity entity = convertToEntityLite(dto);
            if(entity != null) {
                dtos.add(dto);
            }
        }

        return entities;
    }

    public static PostAnimalEntity convertToEntityLite(PostAnimalDTO dto) {
        if(dto == null) {
            return null;
        }

        return PostAnimalEntity.builder()
                .idPostAnimal(dto.getIdPostAnimal())
                .build();
    }

    public static PostAnimalDTO convertToDTO(PostAnimalEntity entity, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToDTOLite(entity);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToDTO(entity);
        }
        return null;
    }

    public static PostAnimalEntity convertToEntity(PostAnimalDTO dto, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToEntityLite(dto);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToEntity(dto);
        }
        return null;
    }

}
