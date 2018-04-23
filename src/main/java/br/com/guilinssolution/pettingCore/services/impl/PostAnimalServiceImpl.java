package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.exception.ConstraintException;
import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.repositories.PostAnimalRepository;
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

    private final PostAnimalRepository repository; //Injeção de dependências do SpringBoot

    private final Validator validator; //Injeção de dependências do SpringBoot

    @Autowired //Injeção de dependências do SpringBoot
    public PostAnimalServiceImpl(PostAnimalRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public ListResultDTO<PostAnimalDTO> findAll(PostAnimalDTO dto, PageDTO page) {

        BooleanExpression query = queryGeneration(dto); //BooleanExpression é usado em QueryDSL para armazenar uma query
        Pageable pageable = PageHelper.getPage(page); //Cria um objeto paginável para nevegação em páginas dos resultados

        return findAll(query, pageable, ConvertType.NORMAL); //método para encontrar todos resultados no banco de dados, de modo paginável
    }                                                        //(passa NORMAL como tipo de conversão para DTO, isto é, uma entidade COM suas
                                                             //entidades filhas - referente a relações One to Many)
    @Override
    public ListResultDTO<PostAnimalDTO> findAllLite(PostAnimalDTO dto, PageDTO page) {

        BooleanExpression query = queryGeneration(dto); //BooleanExpression é usado em QueryDSL para armazenar uma query
        Pageable pageable = PageHelper.getPage(page); //Cria um objeto paginável para nevegação em páginas dos resultados

        return findAll(query, pageable, ConvertType.LITE); //método para encontrar todos resultados no banco de dados, de modo paginável
    }                                                      //(passa LITE como tipo de conversão para DTO, isto é, uma entidade SEM suas
                                                           //entidades filhas - referente a relações One to Many)
    @Override
    public PostAnimalDTO findOne(Integer id) {
        PostAnimalEntity postAnimalEntity = this.repository.getOne(id); //Usa o repositório criado da entidade em questão para selecionar
                                                                        //uma única ocorrência da entidade (a que possui o ID passado)
        this.validator.entityNull(postAnimalEntity); //Assegura que a entidade não seja nula
                                                     //(ver classe Validator - no pacote validation - para maiores detalhes)
        return PostAnimalAdapter.convertToDTO(postAnimalEntity); //Método usado para converter a entidade passada em DTO
    }                                                            //(ver respectivo Adapter - no pacote model.adapter - para mais detalhes)

    @Override
    public PostAnimalDTO save(PostAnimalDTO dto) {
        PostAnimalEntity postAnimalEntity = PostAnimalAdapter.convertToEntity(dto);

        this.validator.entityExist(dto.getIdPostAnimal(), this.repository);

        postAnimalEntity = this.repository.save(postAnimalEntity);
        return PostAnimalAdapter.convertToDTO(postAnimalEntity);
    }

    @Override
    public PostAnimalDTO update(Integer id, PostAnimalDTO dto) {
        PostAnimalEntity vesselPostAnimalEntity = this.repository.getOne(id);

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
        Integer sizePostAnimal = dto.getSizePostAnimal().getSizeValue();
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
