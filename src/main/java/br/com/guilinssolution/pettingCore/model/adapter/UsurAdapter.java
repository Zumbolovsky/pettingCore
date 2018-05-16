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

        List<ContributionEntity> contributionEntitiesForIdDonator = entity.getContributionsForIdDonator();
        List<ContributionDTO> contributionDTOSForIdDonator = new ArrayList<>();
        if (contributionEntitiesForIdDonator != null) {
            contributionEntitiesForIdDonator.forEach(contributionEntityForIdDonator ->
                    contributionDTOSForIdDonator.add(ContributionAdapter.convertToDTO(contributionEntityForIdDonator)));
        }

        List<ContributionEntity> contributionEntitiesForIdRequest = entity.getContributionsForIdRequest();
        List<ContributionDTO> contributionDTOSForIdRequest = new ArrayList<>();
        if (contributionEntitiesForIdRequest != null) {
            contributionEntitiesForIdRequest.forEach(contributionEntityForIdRequest ->
                    contributionDTOSForIdRequest.add(ContributionAdapter.convertToDTO(contributionEntityForIdRequest)));
        }

        List<PostAnimalEntity> postAnimalEntities = entity.getPostAnimalEntities();
        List<PostAnimalDTO> postAnimalDTOS = new ArrayList<>();
        if (postAnimalEntities != null) {
            postAnimalEntities.forEach(postAnimalEntity ->
                    postAnimalDTOS.add(PostAnimalAdapter.convertToDTO(postAnimalEntity)));
        }

        List<PostItemEntity> postItemEntities = entity.getPostItemEntities();
        List<PostItemDTO> postItemDTOS = new ArrayList<>();
        if (postItemEntities != null) {
            postItemEntities.forEach(postItemEntity ->
                    postItemDTOS.add(PostItemAdapter.convertToDTO(postItemEntity)));
        }

        return UsurDTO.builder()
                .idUsur(entity.getIdUsur())
                .addressUsur(entity.getAddressUsur())
                .cellphoneUsur(entity.getCellphoneUsur())
                .cityUsur(entity.getCityUsur())
                .cpfUsur(entity.getCpfUsur())
                .emailUsur(entity.getEmailUsur())
                .nameUsur(entity.getNameUsur())
                .phoneUsur(entity.getPhoneUsur())
                .stateUsur(entity.getStateUsur())
                .contributionsForIdDonator(contributionDTOSForIdDonator)
                .contributionsForIdRequest(contributionDTOSForIdRequest)
                .postAnimalDTOS(postAnimalDTOS)
                .postItemDTOS(postItemDTOS)
                .build();
    }

    public static UsurEntity convertToEntity(UsurDTO dto) {
        if (dto == null) {
            return null;
        }

        List<ContributionDTO> contributionDTOSForIdDonator = dto.getContributionsForIdDonator();
        List<ContributionEntity> contributionEntitiesForIdDonator = new ArrayList<>();
        if (contributionDTOSForIdDonator != null) {
            contributionDTOSForIdDonator.forEach(contributionDTOForIdDonator ->
                    contributionEntitiesForIdDonator.add(ContributionAdapter.convertToEntity(contributionDTOForIdDonator)));
        }

        List<ContributionDTO> contributionDTOSForIdRequest = dto.getContributionsForIdRequest();
        List<ContributionEntity> contributionEntitiesForIdRequest = new ArrayList<>();
        if (contributionDTOSForIdRequest != null) {
            contributionDTOSForIdRequest.forEach(contributionDTOForIdRequest ->
                    contributionEntitiesForIdRequest.add(ContributionAdapter.convertToEntity(contributionDTOForIdRequest)));
        }

        List<PostAnimalDTO> postAnimalDTOS = dto.getPostAnimalDTOS();
        List<PostAnimalEntity> postAnimalEntities = new ArrayList<>();
        if (postAnimalDTOS != null) {
            postAnimalDTOS.forEach(postAnimalDTO ->
                    postAnimalEntities.add(PostAnimalAdapter.convertToEntity(postAnimalDTO)));
        }

        List<PostItemDTO> postItemDTOS = dto.getPostItemDTOS();
        List<PostItemEntity> postItemEntities = new ArrayList<>();
        if (postItemDTOS != null) {
            postItemDTOS.forEach(postItemDTO ->
                    postItemEntities.add(PostItemAdapter.convertToEntity(postItemDTO)));
        }

        return UsurEntity.builder()
                .idUsur(dto.getIdUsur())
                .addressUsur(dto.getAddressUsur())
                .cellphoneUsur(dto.getCellphoneUsur())
                .cityUsur(dto.getCityUsur())
                .cpfUsur(dto.getCpfUsur())
                .emailUsur(dto.getEmailUsur())
                .nameUsur(dto.getNameUsur())
                .phoneUsur(dto.getPhoneUsur())
                .stateUsur(dto.getStateUsur())
                .contributionsForIdDonator(contributionEntitiesForIdDonator)
                .contributionsForIdRequest(contributionEntitiesForIdRequest)
                .postAnimalEntities(postAnimalEntities)
                .postItemEntities(postItemEntities)
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
