package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

import java.util.ArrayList;
import java.util.List;

public class PostAnimalAdapter {

    public static PostAnimalDTO convertToDTO(PostAnimalEntity entity) {
        if(entity == null) {
            return null;
        }

        AnimalEntity animalEntity = entity.getAnimalEntity();
        AnimalDTO animalDTO = AnimalAdapter.convertToDTO(animalEntity);

        UsurEntity usurEntity = entity.getUsurEntity();
        UsurDTO usurDTO = UsurAdapter.convertToDTO(usurEntity);

        return PostAnimalDTO.builder()
                .idPostAnimal(entity.getIdPostAnimal())
                .descriptionPostAnimal(entity.getDescriptionPostAnimal())
//                .imagePostAnimal(entity.getImagePostAnimal())
                .sizePostAnimal(entity.getSizePostAnimal())
                .titlePostAnimal(entity.getTitlePostAnimal())
                .animalDTO(animalDTO)
                .usurDTO(usurDTO)
                .build();
    }

    public static PostAnimalEntity convertToEntity(PostAnimalDTO dto) {
        if(dto == null) {
            return null;
        }

        AnimalDTO animalDTO = dto.getAnimalDTO();
        AnimalEntity animalEntity  = AnimalAdapter.convertToEntity(animalDTO);

        UsurDTO usurDTO = dto.getUsurDTO();
        UsurEntity usurEntity = UsurAdapter.convertToEntity(usurDTO);

        return PostAnimalEntity.builder()
                .idPostAnimal(dto.getIdPostAnimal())
                .descriptionPostAnimal(dto.getDescriptionPostAnimal())
//                .imagePostAnimal(dto.getImagePostAnimal())
                .sizePostAnimal(dto.getSizePostAnimal())
                .titlePostAnimal(dto.getTitlePostAnimal())
                .animalEntity(animalEntity)
                .usurEntity(usurEntity)
                .build();
    }

    public static List<PostAnimalDTO> convertToDTOLite(List<PostAnimalEntity> entities) {
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

    public static List<PostAnimalEntity> convertToEntityLite(List<PostAnimalDTO> dtos) {
        if(dtos == null) {
            return null;
        }
        List<PostAnimalEntity> entities = new ArrayList<>();

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
