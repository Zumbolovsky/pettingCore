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

        UsurEntity usurEntity = entity.getUsurEntity();
        UsurDTO usurDTO = UsurAdapter.convertToDTO(usurEntity);

        return PostItemDTO.builder()
                .idPostItem(entity.getIdPostItem())
                .descriptionPostItem(entity.getDescriptionPostItem())
                .titlePostItem(entity.getTitlePostItem())
                .typePostItem(entity.getTypePostItem())
                .animalDTO(animalDTO)
                .usurDTO(usurDTO)
                .build();
    }

    public static PostItemEntity convertToEntity(PostItemDTO dto) {
        if(dto == null) {
            return null;
        }

        AnimalDTO animalDTO = dto.getAnimalDTO();
        AnimalEntity animalEntity = AnimalAdapter.convertToEntity(animalDTO);

        UsurDTO usurDTO = dto.getUsurDTO();
        UsurEntity usurEntity = UsurAdapter.convertToEntity(usurDTO);

        return PostItemEntity.builder()
                .idPostItem(dto.getIdPostItem())
                .descriptionPostItem(dto.getDescriptionPostItem())
                .titlePostItem(dto.getTitlePostItem())
                .typePostItem(dto.getTypePostItem())
                .animalEntity(animalEntity)
                .usurEntity(usurEntity)
                .build();
    }

    //todo: clean if necessary
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

    public static PostItemDTO convertToDTO(PostItemEntity entity, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToDTOLite(entity);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToDTO(entity);
        }
        return null;
    }

}
