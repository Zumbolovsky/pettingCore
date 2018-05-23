package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.ContributionAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.PostItemAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import br.com.guilinssolution.pettingCore.model.entities.QContributionEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.example.ContributionExample;
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
    public ContributionServiceImpl(ContributionRepository repository, Validator validator, PostAnimalRepository postAnimalRepository, PostItemRepository postItemRepository, UsurRepository usurRepository) {
        this.repository = repository;
        this.validator = validator;
        this.postAnimalRepository = postAnimalRepository;
        this.postItemRepository = postItemRepository;
        this.usurRepository = usurRepository;
    }

    @Override
    public ListResultDTO<ContributionDTO> findAll(ContributionExample example, PageDTO page) {
        BooleanExpression query = queryGeneration(example);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<ContributionDTO> findAllLite(ContributionExample example, PageDTO page) {
        BooleanExpression query = queryGeneration(example);
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
                                Integer idUsurRequest, Integer idUsurDonator) {
        this.validator.entityNotExist(idUsurRequest, this.usurRepository);
        dto.setUsurDTOByIdRequest(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsurRequest)));

        validateAndAddPosts(dto, idPostAnimal, idPostItem);

        if(idUsurDonator != null) {
            this.validator.entityNotExist(idUsurDonator, this.usurRepository);
            dto.setUsurDTOByIdDonator(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsurDonator)));
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

        this.validator.entityNotExist(idUsurRequest, this.usurRepository);
        dto.setUsurDTOByIdRequest(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsurRequest)));

        validateAndAddPosts(dto, idPostAnimal, idPostItem);

        if(idUsurDonator != null) {
            this.validator.entityNotExist(idUsurDonator, this.usurRepository);
            dto.setUsurDTOByIdDonator(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsurDonator)));
        }

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

    private ListResultDTO<ContributionDTO> findAll(BooleanExpression query, Pageable page, ConvertType conversionType) {
        Page<ContributionEntity> contributionEntityPages = this.repository.findAll(query, page);
        List<ContributionDTO> contributionDTOS = new ArrayList<>();

        for (ContributionEntity contributionEntity: contributionEntityPages) {
            ContributionDTO conversionDTO = ContributionAdapter.convertToDTO(contributionEntity, conversionType);
            contributionDTOS.add(conversionDTO);
        }

        return new ListResultDTO<>(contributionEntityPages, contributionDTOS);
    }

    private BooleanExpression queryGeneration(ContributionExample example) {
        QContributionEntity root = QContributionEntity.contributionEntity;

        String descriptionContribution = example.getDescriptionContribution();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (StringUtils.isNotEmpty(descriptionContribution)) {
            expressionsAnd.add(root.descriptionContribution.like("%"+descriptionContribution+"%"));
        }

        return addAnd(expressionsAnd);
    }

    private void validateAndAddPosts(ContributionDTO dto, Integer idPostAnimal, Integer idPostItem) {
        if (idPostAnimal == null && idPostItem == null) {
            this.validator.entityNotExist(null, repository);
        } else if (idPostAnimal == null) {
            this.validator.entityNotExist(idPostItem, this.postItemRepository);
            dto.setPostItemDTO(PostItemAdapter.convertToDTO(this.postItemRepository.getOne(idPostItem)));
        } else if (idPostItem == null) {
            this.validator.entityNotExist(idPostAnimal, this.postAnimalRepository);
            dto.setPostAnimalDTO(PostAnimalAdapter.convertToDTO(this.postAnimalRepository.getOne(idPostAnimal)));
        } else {
            this.validator.entityNotExist(idPostAnimal, this.postAnimalRepository);
            this.validator.entityNotExist(idPostItem, this.postItemRepository);
            dto.setPostAnimalDTO(PostAnimalAdapter.convertToDTO(this.postAnimalRepository.getOne(idPostAnimal)));
            dto.setPostItemDTO(PostItemAdapter.convertToDTO(this.postItemRepository.getOne(idPostItem)));
        }
    }

}
