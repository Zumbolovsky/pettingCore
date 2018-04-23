package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.exception.ConstraintException;
import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostItemAdapter;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostItemEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.repositories.PostItemRepository;
import br.com.guilinssolution.pettingCore.services.PostItemService;
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
public class PostItemServiceImpl implements PostItemService {

    private final PostItemRepository repository;

    private final Validator validator;

    @Autowired
    public PostItemServiceImpl(PostItemRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public ListResultDTO<PostItemDTO> findAll(PostItemDTO dto, PageDTO page) {

        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<PostItemDTO> findAllLite(PostItemDTO dto, PageDTO page) {

        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public PostItemDTO findOne(Integer id) {
        PostItemEntity postItemEntity = this.repository.getOne(id);

        this.validator.entityNull(postItemEntity);

        return PostItemAdapter.convertToDTO(postItemEntity);
    }

    @Override
    public PostItemDTO save(PostItemDTO dto) {
        PostItemEntity postItemEntity = PostItemAdapter.convertToEntity(dto);

        this.validator.entityExist(dto.getIdPostItem(), this.repository);

        postItemEntity = this.repository.save(postItemEntity);
        return PostItemAdapter.convertToDTO(postItemEntity);
    }

    @Override
    public PostItemDTO update(Integer id, PostItemDTO dto) {
        PostItemEntity vesselPostItemEntity = this.repository.getOne(id);

        this.validator.entityNull(vesselPostItemEntity);

        PostItemEntity newPostItemEntity = PostItemAdapter.convertToEntity(dto);
        vesselPostItemEntity.update(newPostItemEntity);

        newPostItemEntity = this.repository.save(vesselPostItemEntity);
        return PostItemAdapter.convertToDTO(newPostItemEntity);
    }

    @Override
    public void delete(Integer id) {
        try {
            PostItemEntity entity = this.repository.getOne(id);

            this.validator.entityNull(entity);

            this.repository.deleteById(id);
        } catch (Exception e) {
            String errorMessage = "Valor inválido para a constraint !";
            throw new ConstraintException(errorMessage, HttpStatus.BAD_REQUEST);
        }

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

    private BooleanExpression queryGeneration(PostItemDTO dto) {

        QPostItemEntity root = QPostItemEntity.postItemEntity;

        Integer idPostItem = dto.getIdPostItem();
        String descriptionPostItem = dto.getDescriptionPostItem();
        String titlePostItem = dto.getTitlePostItem();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (idPostItem != null) {
            expressionsAnd.add(root.idPostItem.eq(idPostItem));
        }
        if (StringUtils.isNotEmpty(descriptionPostItem)) {
            expressionsAnd.add(root.descriptionPostItem.like("%"+descriptionPostItem+"%"));
        }
        if (StringUtils.isNotEmpty(titlePostItem)) {
            expressionsAnd.add(root.titlePostItem.like("%"+titlePostItem+"%"));
        }

        return addAnd(expressionsAnd);
    }

}
