package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.Size;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.model.example.PostAnimalExample;
import br.com.guilinssolution.pettingCore.repositories.PostAnimalRepository;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.PostAnimalService;
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

/////////////////////////////////////////
// TODO: Implementar serviço para imagem
/////////////////////////////////////////

@Service
public class PostAnimalServiceImpl implements PostAnimalService {

    private final PostAnimalRepository repository;

    private final UsurRepository usurRepository;

    private final Validator validator;

    @Autowired
    public PostAnimalServiceImpl(PostAnimalRepository repository, UsurRepository usurRepository, Validator validator) {
        this.repository = repository;
        this.usurRepository = usurRepository;
        this.validator = validator;
    }

    @Override
    public ListResultDTO<PostAnimalDTO> findAll(PostAnimalExample example, Species species, PageDTO page) {
        example.setSpeciesPostAnimal(species);
        BooleanExpression query = queryGeneration(example);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<PostAnimalDTO> listByUsur(Integer idUsur, PageDTO pageDTO) {
        return this.repository.listByUsur(idUsur, pageDTO);
    }

    @Override
    public ListResultDTO<PostAnimalDTO> findAllLite(PostAnimalExample example, PageDTO page) {
        BooleanExpression query = queryGeneration(example);
        Pageable pageable = PageHelper.getPageLite(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public PostAnimalDTO findOne(Integer id) {
        PostAnimalEntity postAnimalEntity = this.repository.getOne(id);

        this.validator.entityNull(postAnimalEntity);
        this.validator.entityNotExist(id, this.repository);

        return PostAnimalAdapter.convertToDTO(postAnimalEntity);
    }

    @Override
    public PostAnimalDTO save(PostAnimalExample example, Integer idUsur) {
        this.validator.entityNotExist(idUsur, this.usurRepository);

        PostAnimalDTO dto = buildDTO(example);
        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));
        PostAnimalEntity postAnimalEntity = PostAnimalAdapter.convertToEntity(dto);

        postAnimalEntity = this.repository.save(postAnimalEntity);
        return PostAnimalAdapter.convertToDTO(postAnimalEntity);
    }

    @Override
    public PostAnimalDTO update(Integer currentId, PostAnimalExample example, Integer idUsur) {
        this.validator.entityNotExist(currentId, this.repository);

        PostAnimalEntity vesselPostAnimalEntity = this.repository.getOne(currentId);

        PostAnimalDTO dto = buildDTO(example);
        this.validator.entityNotExist(idUsur, this.usurRepository);
        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));

        PostAnimalEntity newPostAnimalEntity = PostAnimalAdapter.convertToEntity(dto);
        vesselPostAnimalEntity.update(newPostAnimalEntity);

        newPostAnimalEntity = this.repository.save(vesselPostAnimalEntity);
        return PostAnimalAdapter.convertToDTO(newPostAnimalEntity);
    }

    @Override
    public PostAnimalDTO quickUpdate(Integer currentId, PostAnimalExample example) {
        this.validator.entityNotExist(currentId, this.repository);

        PostAnimalEntity vesselPostAnimalEntity = this.repository.getOne(currentId);

        PostAnimalDTO dto = buildDTO(example);
        PostAnimalEntity newPostAnimalEntity = PostAnimalAdapter.convertToEntity(dto);
        newPostAnimalEntity.setUsurEntity(vesselPostAnimalEntity.getUsurEntity());

        vesselPostAnimalEntity.update(newPostAnimalEntity);

        newPostAnimalEntity = this.repository.save(vesselPostAnimalEntity);
        return PostAnimalAdapter.convertToDTO(newPostAnimalEntity);
    }

    @Override
    public void delete(Integer id) {
        PostAnimalEntity entity = this.repository.getOne(id);

        this.validator.entityNotExist(id, this.repository);

        this.repository.delete(entity);
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

    private BooleanExpression queryGeneration(PostAnimalExample example) {
        QPostAnimalEntity root = QPostAnimalEntity.postAnimalEntity;

        String descriptionPostAnimal = example.getDescriptionPostAnimal();
        Size sizePostAnimal = example.getSizePostAnimal();
        String titlePostAnimal = example.getTitlePostAnimal();
        Species speciesPostAnimal = example.getSpeciesPostAnimal();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (StringUtils.isNotEmpty(descriptionPostAnimal)) {
            expressionsAnd.add(root.descriptionPostAnimal.like("%"+descriptionPostAnimal+"%"));
        }
        if (sizePostAnimal != null) {
            expressionsAnd.add(root.sizePostAnimal.eq(sizePostAnimal));
        }
        if (StringUtils.isNotEmpty(titlePostAnimal)) {
            expressionsAnd.add(root.titlePostAnimal.like("%"+titlePostAnimal+"%"));
        }
        if (speciesPostAnimal != null) {
            expressionsAnd.add(root.speciesPostAnimal.eq(speciesPostAnimal));
        }

        return addAnd(expressionsAnd);
    }

    private PostAnimalDTO buildDTO(PostAnimalExample example) {
        return PostAnimalDTO.builder()
                .idPostAnimal(null)
                .titlePostAnimal(example.getTitlePostAnimal())
                .descriptionPostAnimal(example.getDescriptionPostAnimal())
                .sizePostAnimal(example.getSizePostAnimal())
                .speciesPostAnimal(example.getSpeciesPostAnimal())
                .usurDTO(null)
                .build();
    }

}
