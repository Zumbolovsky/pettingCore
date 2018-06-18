package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.ContributionAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.PostItemAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.Kind;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import br.com.guilinssolution.pettingCore.model.entities.QContributionEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.repositories.ContributionRepository;
import br.com.guilinssolution.pettingCore.repositories.PostAnimalRepository;
import br.com.guilinssolution.pettingCore.repositories.PostItemRepository;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.ContributionService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.guilinssolution.pettingCore.helper.SQLHelper.addAnd;

@Service
public class ContributionServiceImpl implements ContributionService {

    private final ContributionRepository repository;

    private final PostAnimalRepository postAnimalRepository;

    private final PostItemRepository postItemRepository;

    private final UsurRepository usurRepository;

    private final Validator validator;

    @Autowired
    public ContributionServiceImpl(ContributionRepository repository, PostAnimalRepository postAnimalRepository, PostItemRepository postItemRepository, UsurRepository usurRepository, Validator validator) {
        this.repository = repository;
        this.postAnimalRepository = postAnimalRepository;
        this.postItemRepository = postItemRepository;
        this.usurRepository = usurRepository;
        this.validator = validator;
    }

    @Override
    public ListResultExample<ContributionDTO> findAll(ContributionDTO dto, PageExample page) {
        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultExample<ContributionDTO> findAllLite(ContributionDTO dto, PageExample page) {
        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public ContributionDTO findOne(Integer id) {
        ContributionEntity contributionEntity = this.repository.getOne(id);

        this.validator.entityNull(contributionEntity);
        this.validator.entityNotExist(id, this.repository);

        return ContributionAdapter.convertToDTO(contributionEntity);
    }

    @Override
    public ContributionDTO save(ContributionDTO dto, Integer idPostAnimal, Integer idPostItem,
                                Integer idUsurRequest, Integer idUsurDonator, Custom custom) {
        if (custom.equals(Custom.CUSTOM)) {
            this.validator.entityNotExist(idUsurDonator, this.usurRepository);
            if (idPostAnimal != null) {
                this.validator.entityNotExist(idPostAnimal, this.postAnimalRepository);
                PostAnimalEntity entity = this.postAnimalRepository.getOne(idPostAnimal);
                dto.setUsurDTOByIdRequest(UsurAdapter.convertToDTO(entity.getUsurEntity()));
                dto.setPostAnimalDTO(PostAnimalAdapter.convertToDTO(entity));
                dto.setDescriptionContribution(entity.getDescriptionPostAnimal());
            } else if (idPostItem != null) {
                this.validator.entityNotExist(idPostItem, this.postItemRepository);
                PostItemEntity entity = this.postItemRepository.getOne(idPostItem);
                dto.setUsurDTOByIdRequest(UsurAdapter.convertToDTO(entity.getUsurEntity()));
                dto.setPostItemDTO(PostItemAdapter.convertToDTO(entity));
                dto.setDescriptionContribution(entity.getDescriptionPostItem());
            }
            dto.setUsurDTOByIdDonator(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsurDonator)));
        } else {
            validateAndAdd(dto, idPostAnimal, idPostItem, idUsurRequest, idUsurDonator);
        }

        ContributionEntity contributionEntity = ContributionAdapter.convertToEntity(dto);

        contributionEntity = this.repository.save(contributionEntity);
        return ContributionAdapter.convertToDTO(contributionEntity);
    }

    @Override
    public ContributionDTO update(Integer currentId, ContributionDTO dto, Integer idPostAnimal,
                                  Integer idPostItem, Integer idUsurRequest, Integer idUsurDonator) {
        this.validator.entityNotExist(currentId, this.repository);
        ContributionEntity vesselContributionEntity = this.repository.getOne(currentId);

        validateAndAdd(dto, idPostAnimal, idPostItem, idUsurRequest, idUsurDonator);

        ContributionEntity newContributionEntity = ContributionAdapter.convertToEntity(dto);

        vesselContributionEntity.update(newContributionEntity);

        newContributionEntity = this.repository.save(vesselContributionEntity);
        return ContributionAdapter.convertToDTO(newContributionEntity);
    }

    @Override
    public ContributionDTO quickUpdate(Integer currentId, ContributionDTO dto) {
        this.validator.entityNotExist(currentId, this.repository);

        ContributionEntity vesselContributionEntity = this.repository.getOne(currentId);

        ContributionEntity newContributionEntity = ContributionAdapter.convertToEntity(dto);
        newContributionEntity.setPostAnimalEntity(vesselContributionEntity.getPostAnimalEntity());
        newContributionEntity.setPostItemEntity(vesselContributionEntity.getPostItemEntity());
        newContributionEntity.setUsurEntityByIdRequest(vesselContributionEntity.getUsurEntityByIdRequest());
        newContributionEntity.setUsurEntityByIdDonator(vesselContributionEntity.getUsurEntityByIdDonator());

        vesselContributionEntity.update(newContributionEntity);

        newContributionEntity = this.repository.save(vesselContributionEntity);
        return ContributionAdapter.convertToDTO(newContributionEntity);
    }

    @Override
    public void delete(Integer id) {
        ContributionEntity entity = this.repository.getOne(id);

        this.validator.entityNotExist(id, this.repository);

        this.repository.delete(entity);
    }

    @Override
    public ListResultExample<ContributionDTO> listByDonator(Integer idUsur, PageExample pageExample, Kind kind) {
        return this.repository.listByDonator(idUsur, pageExample, kind);
    }

    private ListResultExample<ContributionDTO> findAll(BooleanExpression query, Pageable page, ConvertType conversionType) {
        Page<ContributionEntity> contributionEntityPages = this.repository.findAll(query, page);
        List<ContributionDTO> contributionDTOS = new ArrayList<>();

        for (ContributionEntity contributionEntity: contributionEntityPages) {
            ContributionDTO conversionDTO = ContributionAdapter.convertToDTO(contributionEntity, conversionType);
            contributionDTOS.add(conversionDTO);
        }

        return new ListResultExample<>(contributionEntityPages, contributionDTOS);
    }

    private BooleanExpression queryGeneration(ContributionDTO dto) {
        QContributionEntity root = QContributionEntity.contributionEntity;

        String descriptionContribution = dto.getDescriptionContribution();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (StringUtils.isNotEmpty(descriptionContribution)) {
            expressionsAnd.add(root.descriptionContribution.like("%"+descriptionContribution+"%"));
        }

        return addAnd(expressionsAnd);
    }

    private void validateAndAdd(ContributionDTO dto, Integer idPostAnimal, Integer idPostItem,
                                Integer idUsurRequest, Integer idUsurDonator) {
        this.validator.entityNotExist(idUsurRequest, this.usurRepository);
        dto.setUsurDTOByIdRequest(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsurRequest)));
        this.validator.entityNotExist(idUsurDonator, this.usurRepository);
        dto.setUsurDTOByIdDonator(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsurDonator)));
        if ((idPostAnimal == null && idPostItem == null) || (idPostAnimal != null && idPostItem != null)) {
            this.validator.entityNotExist(null, repository);
        } else if (idPostAnimal == null) {
            this.validator.entityNotExist(idPostItem, this.postItemRepository);
            dto.setPostItemDTO(PostItemAdapter.convertToDTO(this.postItemRepository.getOne(idPostItem)));
        } else {
            this.validator.entityNotExist(idPostAnimal, this.postAnimalRepository);
            dto.setPostAnimalDTO(PostAnimalAdapter.convertToDTO(this.postAnimalRepository.getOne(idPostAnimal)));
        }
    }

}
