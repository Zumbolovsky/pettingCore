package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

public class PostAnimalAdapter {

    public static PostAnimalDTO convertToDTO(PostAnimalEntity entity) {
        if(entity == null) {
            return null;
        }

        UsurEntity usurEntity = entity.getUsurEntity();
        UsurDTO usurDTO = UsurAdapter.convertToDTO(usurEntity);

        return PostAnimalDTO.builder()
                .idPostAnimal(entity.getIdPostAnimal())
                .descriptionPostAnimal(entity.getDescriptionPostAnimal())
//                .imagePostAnimal(entity.getImagePostAnimal())
                .sizePostAnimal(entity.getSizePostAnimal())
                .titlePostAnimal(entity.getTitlePostAnimal())
                .speciesPostAnimal(entity.getSpeciesPostAnimal())
                .usurDTO(usurDTO)
                .build();
    }

    public static PostAnimalEntity convertToEntity(PostAnimalDTO dto) {
        if(dto == null) {
            return null;
        }

        UsurDTO usurDTO = dto.getUsurDTO();
        UsurEntity usurEntity = UsurAdapter.convertToEntity(usurDTO);

        return PostAnimalEntity.builder()
                .idPostAnimal(dto.getIdPostAnimal())
                .descriptionPostAnimal(dto.getDescriptionPostAnimal())
//                .imagePostAnimal(dto.getImagePostAnimal())
                .sizePostAnimal(dto.getSizePostAnimal())
                .titlePostAnimal(dto.getTitlePostAnimal())
                .speciesPostAnimal(dto.getSpeciesPostAnimal())
                .usurEntity(usurEntity)
                .build();
    }

    private static PostAnimalDTO convertToDTOLite(PostAnimalEntity entity) {
        if(entity == null) {
            return null;
        }

        return PostAnimalDTO.builder()
                .idPostAnimal(entity.getIdPostAnimal())
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

}
