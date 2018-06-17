package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.Size;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.repositories.PostAnimalRepository;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.PostAnimalService;
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
    public ListResultExample<PostAnimalDTO> findAll(PostAnimalDTO dto, Species species, Custom custom, PageExample page) {
        dto.setSpeciesPostAnimal(species);
        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        if (custom.equals(Custom.CUSTOM)) {
            ListResultExample<PostAnimalDTO> listResultExample = this.repository.findAllCustom(dto, pageable);
            List<PostAnimalDTO> customList = buildCustomList(listResultExample);
            Page<PostAnimalDTO> customPage = new PageImpl<>(customList, pageable, pageable.getPageSize());
            return new ListResultExample<>(customPage, customList);
        }
        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public Integer customCount(Species species) {
        return this.repository.customCount(species);
    }

    @Override
    public ListResultExample<PostAnimalDTO> listByUsur(Integer idUsur, PageExample pageExample) {
        return this.repository.listByUsur(idUsur, pageExample);
    }

    @Override
    public ListResultExample<PostAnimalDTO> findAllLite(PostAnimalDTO dto, PageExample page) {
        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPageLite(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public PostAnimalDTO findOne(Integer id, Custom custom) {
        PostAnimalEntity postAnimalEntity = this.repository.getOne(id);

        this.validator.entityNull(postAnimalEntity);
        this.validator.entityNotExist(id, this.repository);

        if (custom.equals(Custom.CUSTOM)) {
            postAnimalEntity.setUsurEntity(UsurEntity.builder()
                    .idUsur(postAnimalEntity.getUsurEntity().getIdUsur())
                    .nameUsur(postAnimalEntity.getUsurEntity().getNameUsur())
                    .build());
        }
        return PostAnimalAdapter.convertToDTO(postAnimalEntity);
    }

    @Override
    public PostAnimalDTO save(PostAnimalDTO dto, Integer idUsur) {
        this.validator.entityNotExist(idUsur, this.usurRepository);

        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));
        PostAnimalEntity postAnimalEntity = PostAnimalAdapter.convertToEntity(dto);

        postAnimalEntity = this.repository.save(postAnimalEntity);
        return PostAnimalAdapter.convertToDTO(postAnimalEntity);
    }

    @Override
    public PostAnimalDTO update(Integer currentId, PostAnimalDTO dto, Integer idUsur) {
        this.validator.entityNotExist(currentId, this.repository);

        PostAnimalEntity vesselPostAnimalEntity = this.repository.getOne(currentId);

        this.validator.entityNotExist(idUsur, this.usurRepository);
        dto.setUsurDTO(UsurAdapter.convertToDTO(this.usurRepository.getOne(idUsur)));

        PostAnimalEntity newPostAnimalEntity = PostAnimalAdapter.convertToEntity(dto);
        vesselPostAnimalEntity.update(newPostAnimalEntity);

        newPostAnimalEntity = this.repository.save(vesselPostAnimalEntity);
        return PostAnimalAdapter.convertToDTO(newPostAnimalEntity);
    }

    @Override
    public PostAnimalDTO quickUpdate(Integer currentId, PostAnimalDTO dto) {
        this.validator.entityNotExist(currentId, this.repository);

        PostAnimalEntity vesselPostAnimalEntity = this.repository.getOne(currentId);

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

    private ListResultExample<PostAnimalDTO> findAll(BooleanExpression query, Pageable page, ConvertType conversionType) {
        Page<PostAnimalEntity> postAnimalEntityPages = this.repository.findAll(query, page);
        List<PostAnimalDTO> postAnimalDTOS = new ArrayList<>();

        for (PostAnimalEntity postAnimalEntity: postAnimalEntityPages) {
            PostAnimalDTO conversionDTO = PostAnimalAdapter.convertToDTO(postAnimalEntity, conversionType);
            postAnimalDTOS.add(conversionDTO);
        }

        return new ListResultExample<>(postAnimalEntityPages, postAnimalDTOS);
    }

    private BooleanExpression queryGeneration(PostAnimalDTO dto) {
        QPostAnimalEntity root = QPostAnimalEntity.postAnimalEntity;

        String descriptionPostAnimal = dto.getDescriptionPostAnimal();
        Size sizePostAnimal = dto.getSizePostAnimal();
        String titlePostAnimal = dto.getTitlePostAnimal();
        Species speciesPostAnimal = dto.getSpeciesPostAnimal();

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

    private List<PostAnimalDTO> buildCustomList(ListResultExample<PostAnimalDTO> listResultExample) {
        return listResultExample.getContent().stream()
                .map(item -> PostAnimalDTO.builder()
                        .idPostAnimal(item.getIdPostAnimal())
                        .titlePostAnimal(item.getTitlePostAnimal())
                        .descriptionPostAnimal(item.getDescriptionPostAnimal())
                        .sizePostAnimal(item.getSizePostAnimal()).build())
                .collect(Collectors.toList());
    }

}
