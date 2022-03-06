package br.com.guilinssolution.pettingCore.model.adapter;

import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;

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
//                .imageUsur(entity.getImageUsur())
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
//                .imageUsur(dto.getImageUsur())
                .nameUsur(dto.getNameUsur())
                .passwordUsur(dto.getPasswordUsur())
                .phoneUsur(dto.getPhoneUsur())
                .stateUsur(dto.getStateUsur())
                .build();
    }

    private static UsurDTO convertToDTOLite(UsurEntity entity) {
        if(entity == null) {
            return null;
        }

        return UsurDTO.builder()
                .idUsur(entity.getIdUsur())
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

}
