package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.AnimalAdapter;
import br.com.guilinssolution.pettingCore.model.dto.AnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QAnimalEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.repositories.AnimalRepository;
import br.com.guilinssolution.pettingCore.services.AnimalService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.guilinssolution.pettingCore.helper.SQLHelper.addAnd;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository repository;

    private final Validator validator;

    @Autowired
    public AnimalServiceImpl(AnimalRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public ListResultDTO<AnimalDTO> findAll(AnimalDTO dto, PageDTO page) {
        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<AnimalDTO> findAllLite(AnimalDTO dto, PageDTO page) {
        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public AnimalDTO findOne(Integer id) {
        Optional<AnimalEntity> animalEntity = this.repository.findById(id);

        this.validator.entityNull(animalEntity);
        this.validator.entityNotExist(id, this.repository);

        return AnimalAdapter.convertToDTO(animalEntity.get());
    }

    @Override
    public AnimalDTO save(AnimalDTO dto) {
        AnimalEntity animalEntity = AnimalAdapter.convertToEntity(dto);

        this.validator.entityExistByEntity(animalEntity, this.repository);
        animalEntity = this.repository.save(animalEntity);

        return AnimalAdapter.convertToDTO(animalEntity);
    }

    @Override
    public AnimalDTO update(Integer currentId, AnimalDTO dto) {
        this.validator.entityNotExist(currentId, this.repository);

        AnimalEntity vesselAnimalEntity = this.repository.getOne(currentId);

        AnimalEntity newAnimalEntity = AnimalAdapter.convertToEntity(dto);
        this.validator.entityExistByEntity(newAnimalEntity, this.repository);
        vesselAnimalEntity.update(newAnimalEntity);

        newAnimalEntity = this.repository.save(vesselAnimalEntity);
        return AnimalAdapter.convertToDTO(newAnimalEntity);
    }

    @Override
    public void delete(Integer id) {
        AnimalEntity entity = this.repository.getOne(id);

        this.validator.entityNotExist(id, this.repository);

        this.repository.delete(entity);
    }

    private ListResultDTO<AnimalDTO> findAll(BooleanExpression query, Pageable page, ConvertType conversionType) {
        Page<AnimalEntity> animalEntityPages = this.repository.findAll(query, page);
        List<AnimalDTO> animalDTOS = new ArrayList<>();

        for (AnimalEntity animalEntity: animalEntityPages) {
            AnimalDTO conversionDTO = AnimalAdapter.convertToDTO(animalEntity, conversionType);
            animalDTOS.add(conversionDTO);
        }

        return new ListResultDTO<>(animalEntityPages, animalDTOS);
    }

    private BooleanExpression queryGeneration(AnimalDTO dto) {
        QAnimalEntity root = QAnimalEntity.animalEntity;

        Integer idAnimal = dto.getIdAnimal();
        String breedAnimal = dto.getBreedAnimal();
        Species speciesAnimal = dto.getSpeciesAnimal();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (idAnimal != null) {
            expressionsAnd.add(root.idAnimal.eq(idAnimal));
        }
        if (StringUtils.isNotEmpty(breedAnimal)) {
            expressionsAnd.add(root.breedAnimal.like("%"+breedAnimal+"%"));
        }
        if (speciesAnimal != null) {
            expressionsAnd.add(root.speciesAnimal.eq(speciesAnimal));
        }

        return addAnd(expressionsAnd);
    }

}
