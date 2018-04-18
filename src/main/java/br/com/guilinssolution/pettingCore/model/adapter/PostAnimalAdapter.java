package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;

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
                .sizePostAnimal(entity.getSizePostAnimal())
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
        Set<ContributionEntity> contributionEntities = new HashSet<>();
        for (ContributionDTO contributionDTO : contributionDTOS) {
            ContributionEntity contributionEntity = ContributionAdapter.convertToEntity(contributionDTO);
            contributionEntities.add(contributionEntity);
        }

        List<UsurDTO> usurDTOS = dto.getUsurDTOS();
        Set<UsurEntity> usurEntities = new HashSet<>();
        for (UsurDTO usurDTO : usurDTOS) {
            UsurEntity usurEntity = UsurAdapter.convertToEntity(usurDTO);
            usurEntities.add(usurEntity);
        }

        return PostAnimalEntity.builder()
                .idPostAnimal(dto.getIdPostAnimal())
                .descriptionPostAnimal(dto.getDescriptionPostAnimal())
                .imagePostAnimal(dto.getImagePostAnimal())
                .sizePostAnimal(dto.getSizePostAnimal())
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

}
