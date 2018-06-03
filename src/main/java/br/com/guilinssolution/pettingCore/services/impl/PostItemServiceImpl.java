package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.AnimalAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.PostItemAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostItemEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.example.PostItemExample;
import br.com.guilinssolution.pettingCore.repositories.AnimalRepository;
import br.com.guilinssolution.pettingCore.repositories.PostItemRepository;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.PostItemService;
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
public class PostItemServiceImpl implements PostItemService {

    private final PostItemRepository repository;

    private final AnimalRepository animalRepository;

    private final UsurRepository usurRepository;

    private final Validator validator;

    @Autowired
    public PostItemServiceImpl(PostItemRepository repository, Validator validator, AnimalRepository animalRepository, UsurRepository usurRepository) {
        this.repository = repository;
        this.validator = validator;
        this.animalRepository = animalRepository;
        this.usurRepository = usurRepository;
    }

    @Override
    public ListResultDTO<PostItemDTO> findAll(PostItemExample example, PageDTO page) {
        BooleanExpression query = queryGeneration(example);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<PostItemDTO> findAllLite(PostItemExample example, PageDTO page) {
        BooleanExpression query = queryGeneration(example);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public PostItemDTO findOne(Integer id) {
        PostItemEntity postItemEntity = this.repository.getOne(id);

        this.validator.entityNull(postItemEntity);
        this.validator.entityNotExist(id, this.repository);

        return PostItemAdapter.convertToDTO(postItemEntity);
    }

    @Override
    public PostItemDTO save(PostItemDTO dto, Integer idAnimal, Integer idUsur) {
        this.validator.entityNotExist(idAnimal, this.animalRepository);
        this.validator.entityNotExist(idUsur, this.usurRepository);

        dto.setAnimalDTO(AnimalAdapter.convertToDTO(this.animalRepository.getOne(idAnimal)));
        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));
        PostItemEntity postItemEntity = PostItemAdapter.convertToEntity(dto);

        postItemEntity = this.repository.save(postItemEntity);
        return PostItemAdapter.convertToDTO(postItemEntity);
    }

    @Override
    public PostItemDTO update(Integer currentId, PostItemDTO dto, Integer idAnimal, Integer idUsur) {
        this.validator.entityNotExist(currentId, this.repository);

        PostItemEntity vesselPostItemEntity = this.repository.getOne(currentId);

        this.validator.entityNotExist(idAnimal, this.animalRepository);
        dto.setAnimalDTO(AnimalAdapter.convertToDTO(this.animalRepository.getOne(idAnimal)));

        this.validator.entityNotExist(idUsur, this.usurRepository);
        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));

        PostItemEntity newPostItemEntity = PostItemAdapter.convertToEntity(dto);
        vesselPostItemEntity.update(newPostItemEntity);

        newPostItemEntity = this.repository.save(vesselPostItemEntity);
        return PostItemAdapter.convertToDTO(newPostItemEntity);
    }

    @Override
    public PostItemDTO quickUpdate(Integer currentId, PostItemDTO dto) {
        this.validator.entityNotExist(currentId, this.repository);

        PostItemEntity vesselPostItemEntity = this.repository.getOne(currentId);

        PostItemEntity newPostItemEntity = PostItemAdapter.convertToEntity(dto);
        newPostItemEntity.setAnimalEntity(vesselPostItemEntity.getAnimalEntity());
        newPostItemEntity.setUsurEntity(vesselPostItemEntity.getUsurEntity());
        vesselPostItemEntity.update(newPostItemEntity);

        newPostItemEntity = this.repository.save(vesselPostItemEntity);
        return PostItemAdapter.convertToDTO(newPostItemEntity);
    }

    @Override
    public void delete(Integer id) {
        PostItemEntity entity = this.repository.getOne(id);

        this.validator.entityNotExist(id, this.repository);

        this.repository.delete(entity);
    }

    @Override
    public ListResultDTO<PostItemDTO> listByUsur(Integer idUsur, PageDTO pageDTO) {
        return this.repository.listByUsur(idUsur, pageDTO);
    }

    private ListResultDTO<PostItemDTO> findAll(BooleanExpression query, Pageable page, ConvertType conversionType) {
        Page<PostItemEntity> postItemEntityPages = this.repository.findAll(query, page);
        List<PostItemDTO> postItemDTOS = new ArrayList<>();

        for (PostItemEntity postItemEntity: postItemEntityPages) {
            PostItemDTO conversionDTO = PostItemAdapter.convertToDTO(postItemEntity, conversionType);
            postItemDTOS.add(conversionDTO);
        }

        return new ListResultDTO<>(postItemEntityPages, postItemDTOS);
    }

    private BooleanExpression queryGeneration(PostItemExample example) {
        QPostItemEntity root = QPostItemEntity.postItemEntity;

        String descriptionPostItem = example.getDescriptionPostItem();
        String titlePostItem = example.getTitlePostItem();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (StringUtils.isNotEmpty(descriptionPostItem)) {
            expressionsAnd.add(root.descriptionPostItem.like("%"+descriptionPostItem+"%"));
        }
        if (StringUtils.isNotEmpty(titlePostItem)) {
            expressionsAnd.add(root.titlePostItem.like("%"+titlePostItem+"%"));
        }

        return addAnd(expressionsAnd);
    }

}
