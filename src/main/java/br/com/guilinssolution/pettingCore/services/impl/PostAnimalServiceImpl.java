package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.exception.ConstraintException;
import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.AnimalAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.Size;
import br.com.guilinssolution.pettingCore.repositories.AnimalRepository;
import br.com.guilinssolution.pettingCore.repositories.PostAnimalRepository;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.PostAnimalService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.guilinssolution.pettingCore.helper.SQLHelper.addAnd;

/////////////////////////////////////////
// TODO: Implementar serviço para imagem
/////////////////////////////////////////

@Service
public class PostAnimalServiceImpl implements PostAnimalService {

    private final PostAnimalRepository repository;

    private final AnimalRepository animalRepository;

    private final UsurRepository usurRepository;

    private final Validator validator;

    @Autowired
    public PostAnimalServiceImpl(PostAnimalRepository repository, AnimalRepository animalRepository, Validator validator, UsurRepository usurRepository) {
        this.repository = repository;
        this.animalRepository = animalRepository;
        this.validator = validator;
        this.usurRepository = usurRepository;
    }

    @Override
    public ListResultDTO<PostAnimalDTO> findAll(PostAnimalDTO dto, PageDTO page) {

        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<PostAnimalDTO> findAllLite(PostAnimalDTO dto, PageDTO page) {

        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public PostAnimalDTO findOne(Integer id) {
        PostAnimalEntity postAnimalEntity = this.repository.getOne(id);

        this.validator.entityNull(postAnimalEntity);

        return PostAnimalAdapter.convertToDTO(postAnimalEntity);
    }

    @Override
    public PostAnimalDTO save(PostAnimalDTO dto, Integer idAnimal, Integer idUsur) {
        this.validator.entityNotExist(idAnimal, this.animalRepository);
        this.validator.entityNotExist(idUsur, this.usurRepository);

        dto.setAnimalDTO(AnimalAdapter.convertToDTO(this.animalRepository.getOne(idAnimal)));
        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));
        PostAnimalEntity postAnimalEntity = PostAnimalAdapter.convertToEntity(dto);

        postAnimalEntity = this.repository.save(postAnimalEntity);
        return PostAnimalAdapter.convertToDTO(postAnimalEntity);
    }

    @Override
    public PostAnimalDTO update(Integer currentId, PostAnimalDTO dto, Integer idAnimal, Integer idUsur) {
        this.validator.entityNotExist(currentId, this.repository);

        PostAnimalEntity vesselPostAnimalEntity = this.repository.getOne(currentId);

        this.validator.entityNotExist(idAnimal, this.animalRepository);
        dto.setAnimalDTO(AnimalAdapter.convertToDTO(this.animalRepository.getOne(idAnimal)));

        this.validator.entityNotExist(idUsur, this.usurRepository);
        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));

        this.validator.entityNull(vesselPostAnimalEntity);

        PostAnimalEntity newPostAnimalEntity = PostAnimalAdapter.convertToEntity(dto);
        vesselPostAnimalEntity.update(newPostAnimalEntity);

        newPostAnimalEntity = this.repository.save(vesselPostAnimalEntity);
        return PostAnimalAdapter.convertToDTO(newPostAnimalEntity);
    }

    @Override
    public void delete(Integer id) {
        try {
            PostAnimalEntity entity = this.repository.getOne(id);

            this.validator.entityNull(entity);

            this.repository.deleteById(id);
        } catch (Exception e) {
            String errorMessage = "Valor inválido para a constraint !";
            throw new ConstraintException(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    private ListResultDTO<PostAnimalDTO> findAll(BooleanExpression query, Pageable page, ConvertType conversionType) {

        Page<PostAnimalEntity> postAnimalEntityPages = this.repository.findAll(query, page);
        List<PostAnimalDTO> postAnimalDTOS = new ArrayList<>();

        for (PostAnimalEntity postAnimalEntity: postAnimalEntityPages) {
            PostAnimalDTO conversionDTO = PostAnimalAdapter.convertToDTO(postAnimalEntity, conversionType);
            postAnimalDTOS.add(conversionDTO);
        }

        return new ListResultDTO<>(postAnimalEntityPages, postAnimalDTOS);
    }

    private BooleanExpression queryGeneration(PostAnimalDTO dto) {

        QPostAnimalEntity root = QPostAnimalEntity.postAnimalEntity;

        Integer idPostAnimal = dto.getIdPostAnimal();
        String descriptionPostAnimal = dto.getDescriptionPostAnimal();
        Size sizePostAnimal = dto.getSizePostAnimal();
        String titlePostAnimal = dto.getTitlePostAnimal();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (idPostAnimal != null) {
            expressionsAnd.add(root.idPostAnimal.eq(idPostAnimal));
        }
        if (StringUtils.isNotEmpty(descriptionPostAnimal)) {
            expressionsAnd.add(root.descriptionPostAnimal.like("%"+descriptionPostAnimal+"%"));
        }
        if (sizePostAnimal != null) {
            expressionsAnd.add(root.sizePostAnimal.eq(sizePostAnimal));
        }
        if (StringUtils.isNotEmpty(titlePostAnimal)) {
            expressionsAnd.add(root.titlePostAnimal.like("%"+titlePostAnimal+"%"));
        }

        return addAnd(expressionsAnd);
    }

}
