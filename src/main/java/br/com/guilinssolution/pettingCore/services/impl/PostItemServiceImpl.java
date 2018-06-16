package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostItemAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostItemEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.model.enums.Type;
import br.com.guilinssolution.pettingCore.repositories.PostItemRepository;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.PostItemService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.guilinssolution.pettingCore.helper.SQLHelper.addAnd;

/////////////////////////////////////////
// TODO: Implementar serviço para imagem
/////////////////////////////////////////

@Service
public class PostItemServiceImpl implements PostItemService {

    private final PostItemRepository repository;

    private final UsurRepository usurRepository;

    private final Validator validator;

    @Autowired
    public PostItemServiceImpl(PostItemRepository repository, UsurRepository usurRepository, Validator validator) {
        this.repository = repository;
        this.usurRepository = usurRepository;
        this.validator = validator;
    }

    @Override
    public ListResultDTO<PostItemDTO> findAll(PostItemDTO dto, Type type, Custom custom, PageDTO page) {
        dto.setTypePostItem(type);
        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        if (custom.equals(Custom.CUSTOM)) {
            ListResultDTO<PostItemDTO> listResultDTO = findAll(query, pageable, ConvertType.NORMAL);
            List<PostItemDTO> customList = buildCustomList(listResultDTO);
            Page<PostItemDTO> customPage = new PageImpl<>(customList, pageable, pageable.getPageSize());
            return new ListResultDTO<>(customPage, customList);
        }
        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<PostItemDTO> findAllLite(PostItemDTO dto, PageDTO page) {
        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPageLite(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public ListResultDTO<PostItemDTO> listByUsur(Integer idUsur, PageDTO pageDTO) {
        return this.repository.listByUsur(idUsur, pageDTO);
    }

    @Override
    public PostItemDTO findOne(Integer id) {
        PostItemEntity postItemEntity = this.repository.getOne(id);

        this.validator.entityNull(postItemEntity);
        this.validator.entityNotExist(id, this.repository);

        return PostItemAdapter.convertToDTO(postItemEntity);
    }

    @Override
    public PostItemDTO save(PostItemDTO dto, Integer idUsur) {
        this.validator.entityNotExist(idUsur, this.usurRepository);

        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));
        PostItemEntity postItemEntity = PostItemAdapter.convertToEntity(dto);

        postItemEntity = this.repository.save(postItemEntity);
        return PostItemAdapter.convertToDTO(postItemEntity);
    }

    @Override
    public PostItemDTO update(Integer currentId, PostItemDTO dto, Integer idUsur) {
        this.validator.entityNotExist(currentId, this.repository);

        PostItemEntity vesselPostItemEntity = this.repository.getOne(currentId);

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

        String descriptionPostItem = dto.getDescriptionPostItem();
        String titlePostItem = dto.getTitlePostItem();
        Species speciesPostItem = dto.getSpeciesPostItem();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (StringUtils.isNotEmpty(descriptionPostItem)) {
            expressionsAnd.add(root.descriptionPostItem.like("%"+descriptionPostItem+"%"));
        }
        if (StringUtils.isNotEmpty(titlePostItem)) {
            expressionsAnd.add(root.titlePostItem.like("%"+titlePostItem+"%"));
        }
        if (speciesPostItem != null) {
            expressionsAnd.add(root.speciesPostItem.eq(speciesPostItem));
        }

        return addAnd(expressionsAnd);
    }

    private List<PostItemDTO> buildCustomList(ListResultDTO<PostItemDTO> listResultDTO) {
        return listResultDTO.getContent().stream()
                .map(item -> PostItemDTO.builder()
                        .idPostItem(item.getIdPostItem())
                        .titlePostItem(item.getTitlePostItem())
                        .descriptionPostItem(item.getDescriptionPostItem())
                        .speciesPostItem(item.getSpeciesPostItem()).build())
                .collect(Collectors.toList());
    }

}
