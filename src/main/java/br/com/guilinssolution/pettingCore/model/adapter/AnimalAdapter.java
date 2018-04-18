package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalAdapter {

    public static AnimalDTO convertToDTO(AnimalEntity entity) {
        if (entity == null) {
            return null;
        }

        Set<PostAnimalEntity> postAnimalEntities = entity.getPostAnimalEntities();
        List<PostAnimalDTO> postAnimalDTOS = PostAnimalAdapter.convertToDTOLite(postAnimalEntities);

        Set<PostItemEntity> postItemEntities = entity.getPostItemEntities();
        List<PostItemDTO> postItemDTOS = PostItemAdapter.convertToDTOLite(postItemEntities);

        return AnimalDTO.builder()
                .idAnimal(entity.getIdAnimal())
                .breedAnimal(entity.getBreedAnimal())
                .speciesAnimal(entity.getSpeciesAnimal())
                .postAnimalDTOS(postAnimalDTOS)
                .postItemDTOS(postItemDTOS)
                .build();
    }

    public static AnimalEntity convertToEntity(AnimalDTO dto) {
        if (dto == null) {
            return null;
        }

        List<PostAnimalDTO> postAnimalDTOS = dto.getPostAnimalDTOS();
        Set<PostAnimalEntity> postAnimalEntities = new HashSet<>();
        for (PostAnimalDTO postAnimalDTO : postAnimalDTOS) {
            PostAnimalEntity postAnimalEntity = PostAnimalAdapter.convertToEntity(postAnimalDTO);
            postAnimalEntities.add(postAnimalEntity);
        }

        List<PostItemDTO> postItemDTOS = dto.getPostItemDTOS();
        Set<PostItemEntity> postItemEntities = new HashSet<>();
        for (PostItemDTO postItemDTO : postItemDTOS) {
            PostItemEntity postItemEntity = PostItemAdapter.convertToEntity(postItemDTO);
            postItemEntities.add(postItemEntity);
        }

        return AnimalEntity.builder()
                .idAnimal(dto.getIdAnimal())
                .breedAnimal(dto.getBreedAnimal())
                .speciesAnimal(dto.getSpeciesAnimal())
                .postAnimalEntities(postAnimalEntities)
                .postItemEntities(postItemEntities)
                .build();
    }

    public static List<AnimalDTO> convertToDTOLite(Set<AnimalEntity> entities) {
        if(entities == null) {
            return null;
        }
        List<AnimalDTO> dtos = new ArrayList<>();

        for (AnimalEntity entity : entities) {
            AnimalDTO dto = convertToDTOLite(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }

        return dtos;
    }

    public static AnimalDTO convertToDTOLite(AnimalEntity entity) {
        if(entity == null) {
            return null;
        }

        return AnimalDTO.builder()
                .idAnimal(entity.getIdAnimal())
                .build();
    }
}
