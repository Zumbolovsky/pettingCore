package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

public class AnimalAdapter {

    public static AnimalDTO convertToDTO(AnimalEntity entity) {
        if (entity == null) {
            return null;
        }

        return AnimalDTO.builder()
                .idAnimal(entity.getIdAnimal())
                .breedAnimal(entity.getBreedAnimal())
                .speciesAnimal(entity.getSpeciesAnimal())
                .build();
    }

    public static AnimalEntity convertToEntity(AnimalDTO dto) {
        if (dto == null) {
            return null;
        }

        return AnimalEntity.builder()
                .idAnimal(dto.getIdAnimal())
                .breedAnimal(dto.getBreedAnimal())
                .speciesAnimal(dto.getSpeciesAnimal())
                .build();
    }

    private static AnimalDTO convertToDTOLite(AnimalEntity entity) {
        if(entity == null) {
            return null;
        }

        return AnimalDTO.builder()
                .idAnimal(entity.getIdAnimal())
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

}
