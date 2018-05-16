package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

import java.util.ArrayList;
import java.util.List;

public class UsurAdapter {

    public static UsurDTO convertToDTO(UsurEntity entity) {
        if (entity == null) {
            return null;
        }

        return UsurDTO.builder()
                .idUsur(entity.getIdUsur())
                .addressUsur(entity.getAddressUsur())
                .cellphoneUsur(entity.getCellphoneUsur())
                .cityUsur(entity.getCityUsur())
                .cpfUsur(entity.getCpfUsur())
                .emailUsur(entity.getEmailUsur())
                .nameUsur(entity.getNameUsur())
                .passwordUsur(entity.getPasswordUsur())
                .phoneUsur(entity.getPhoneUsur())
                .stateUsur(entity.getStateUsur())
                .build();
    }

    public static UsurEntity convertToEntity(UsurDTO dto) {
        if (dto == null) {
            return null;
        }

        return UsurEntity.builder()
                .idUsur(dto.getIdUsur())
                .addressUsur(dto.getAddressUsur())
                .cellphoneUsur(dto.getCellphoneUsur())
                .cityUsur(dto.getCityUsur())
                .cpfUsur(dto.getCpfUsur())
                .emailUsur(dto.getEmailUsur())
                .nameUsur(dto.getNameUsur())
                .passwordUsur(dto.getPasswordUsur())
                .phoneUsur(dto.getPhoneUsur())
                .stateUsur(dto.getStateUsur())
                .build();
    }

    public static List<UsurDTO> convertToDTOLite(List<UsurEntity> entities) {
        if(entities == null) {
            return null;
        }
        List<UsurDTO> dtos = new ArrayList<>();

        for (UsurEntity entity : entities) {
            UsurDTO dto = convertToDTOLite(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }

        return dtos;
    }

    public static UsurDTO convertToDTOLite(UsurEntity entity) {
        if(entity == null) {
            return null;
        }

        return UsurDTO.builder()
                .idUsur(entity.getIdUsur())
                .build();
    }

    public static List<UsurEntity> convertToEntityLite(List<UsurDTO> dtos) {
        if(dtos == null) {
            return null;
        }
        List<UsurEntity> entities = new ArrayList<>();

        for (UsurDTO dto : dtos) {
            UsurEntity entity = convertToEntityLite(dto);
            if(entity != null) {
                dtos.add(dto);
            }
        }

        return entities;
    }

    public static UsurEntity convertToEntityLite(UsurDTO dto) {
        if(dto == null) {
            return null;
        }

        return UsurEntity.builder()
                .idUsur(dto.getIdUsur())
                .build();
    }

    public static UsurDTO convertToDTO(UsurEntity entity, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToDTOLite(entity);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToDTO(entity);
        }
        return null;
    }

    public static UsurEntity convertToEntity(UsurDTO dto, ConvertType convertType) {
        if(convertType == ConvertType.LITE) {
            return convertToEntityLite(dto);
        } else if(convertType == ConvertType.NORMAL) {
            return convertToEntity(dto);
        }
        return null;
    }

}
