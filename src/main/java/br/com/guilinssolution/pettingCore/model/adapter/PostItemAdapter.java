package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.*;
import br.com.guilinssolution.pettingCore.model.entities.*;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

public class PostItemAdapter {

    public static PostItemDTO convertToDTO(PostItemEntity entity) {
        if(entity == null) {
            return null;
        }

        UsurEntity usurEntity = entity.getUsurEntity();
        UsurDTO usurDTO = UsurAdapter.convertToDTO(usurEntity);

        return PostItemDTO.builder()
                .idPostItem(entity.getIdPostItem())
                .descriptionPostItem(entity.getDescriptionPostItem())
//                .imagePostItem(entity.getImagePostItem())
                .titlePostItem(entity.getTitlePostItem())
                .typePostItem(entity.getTypePostItem())
                .speciesPostItem(entity.getSpeciesPostItem())
                .usurDTO(usurDTO)
                .build();
    }

    public static PostItemEntity convertToEntity(PostItemDTO dto) {
        if(dto == null) {
            return null;
        }

        UsurDTO usurDTO = dto.getUsurDTO();
        UsurEntity usurEntity = UsurAdapter.convertToEntity(usurDTO);

        return PostItemEntity.builder()
                .idPostItem(dto.getIdPostItem())
                .descriptionPostItem(dto.getDescriptionPostItem())
//                .imagePostItem(dto.getImagePostItem())
                .titlePostItem(dto.getTitlePostItem())
                .typePostItem(dto.getTypePostItem())
                .speciesPostItem(dto.getSpeciesPostItem())
                .usurEntity(usurEntity)
                .build();
    }

    private static PostItemDTO convertToDTOLite(PostItemEntity entity) {
        if(entity == null) {
            return null;
        }

        return PostItemDTO.builder()
                .idPostItem(entity.getIdPostItem())
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

}
