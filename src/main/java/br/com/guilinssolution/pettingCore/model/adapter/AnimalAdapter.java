package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

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
        Set<PostAnimalEntity> postAnimalEntities = PostAnimalAdapter.convertToEntityLite(postAnimalDTOS);

        List<PostItemDTO> postItemDTOS = dto.getPostItemDTOS();
        Set<PostItemEntity> postItemEntities = PostItemAdapter.convertToEntityLite(postItemDTOS);

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

    public static Set<AnimalEntity> convertToEntityLite(List<AnimalDTO> dtos) {
        if(dtos == null) {
            return null;
        }
        Set<AnimalEntity> entities = new HashSet<>();

        for (AnimalDTO dto : dtos) {
            AnimalEntity entity = convertToEntityLite(dto);
            if(entity != null) {
                dtos.add(dto);
            }
        }

        return entities;
    }

    public static AnimalEntity convertToEntityLite(AnimalDTO dto) {
        if (dto == null) {
            return null;
        }

        return AnimalEntity.builder()
                .idAnimal(dto.getIdAnimal())
                .build();
    }

    public static AnimalDTO convertToDTO(AnimalEntity entity, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToDTOLite(entity);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToDTO(entity);
        }
        return null;
    }

    public static AnimalEntity convertToEntity(AnimalDTO dto, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToEntityLite(dto);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToEntity(dto);
        }
        return null;
    }

}
