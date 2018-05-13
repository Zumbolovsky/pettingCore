package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.exception.ConstraintException;
import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.*;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.entities.QUsurEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.State;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.UsurService;
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
public class UsurServiceImpl implements UsurService {

    private final UsurRepository repository;

    private final Validator validator;

    @Autowired
    public UsurServiceImpl(UsurRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public ListResultDTO<UsurDTO> findAll(UsurDTO dto, PageDTO page) {

        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<UsurDTO> findAllLite(UsurDTO dto, PageDTO page) {

        BooleanExpression query = queryGeneration(dto);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public UsurDTO findOne(Integer id) {
        UsurEntity usurEntity = this.repository.getOne(id);

        this.validator.entityNull(usurEntity);
        this.validator.entityNotExist(id, this.repository);

        return UsurAdapter.convertToDTO(usurEntity);
    }

    @Override
    public UsurDTO save(UsurDTO dto, Integer idContribution, Integer idPostAnimal, Integer idPostItem) {

        dto.setContributionDTO(ContributionDTO
                .builder()
                .idContribution(idContribution)
                .build());
        dto.setPostAnimalDTO(PostAnimalDTO
                .builder()
                .idPostAnimal(idPostAnimal)
                .build());
        dto.setPostItemDTO(PostItemDTO
                .builder()
                .idPostItem(idPostItem)
                .build());
        UsurEntity usurEntity = UsurAdapter.convertToEntity(dto);

        usurEntity = this.repository.save(usurEntity);
        return UsurAdapter.convertToDTO(usurEntity);
    }

    @Override
    public UsurDTO update(Integer id, UsurDTO dto, Integer idContribution, Integer idPostAnimal, Integer idPostItem) {
        this.validator.entityNotExist(id, this.repository);

        UsurEntity vesselUsurEntity = this.repository.getOne(id);
        if(!(vesselUsurEntity.getContributionEntity().getIdContribution().equals(idContribution)
            && vesselUsurEntity.getPostAnimalEntity().getIdPostAnimal().equals(idPostAnimal)
            && vesselUsurEntity.getPostItemEntity().getIdPostItem().equals(idPostItem))) {
            dto.setContributionDTO(ContributionDTO
                    .builder()
                    .idContribution(idContribution)
                    .build());
            dto.setPostAnimalDTO(PostAnimalDTO
                    .builder()
                    .idPostAnimal(idPostAnimal)
                    .build());
            dto.setPostItemDTO(PostItemDTO
                    .builder()
                    .idPostItem(idPostItem)
                    .build());
        }

        this.validator.entityNull(vesselUsurEntity);

        UsurEntity newUsurEntity = UsurAdapter.convertToEntity(dto);
        vesselUsurEntity.update(newUsurEntity);

        newUsurEntity = this.repository.save(vesselUsurEntity);
        return UsurAdapter.convertToDTO(newUsurEntity);
    }

    @Override
    public void delete(Integer id) {
        try {
            UsurEntity entity = this.repository.getOne(id);

            this.validator.entityNull(entity);

            this.repository.deleteById(id);
        } catch (Exception e) {
            String errorMessage = "ID do dado inexistente !";
            throw new ConstraintException(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    private ListResultDTO<UsurDTO> findAll(BooleanExpression query, Pageable page, ConvertType conversionType) {
        Page<UsurEntity> usurEntityPages = this.repository.findAll(query, page);
        List<UsurDTO> usurDTOS = new ArrayList<>();

        for (UsurEntity usurEntity : usurEntityPages) {
            UsurDTO conversionDTO = UsurAdapter.convertToDTO(usurEntity, conversionType);
            usurDTOS.add(conversionDTO);
        }

        return new ListResultDTO<>(usurEntityPages, usurDTOS);
    }

    private BooleanExpression queryGeneration(UsurDTO dto) {
        QUsurEntity root = QUsurEntity.usurEntity;

        Integer idUsur = dto.getIdUsur();
        String addressUsur = dto.getAddressUsur();
        String cellphoneUsur = dto.getCellphoneUsur();
        String cityUsur = dto.getCityUsur();
        String cpfUsur = dto.getCpfUsur();
        String emailUsur = dto.getEmailUsur();
        String nameUsur = dto.getNameUsur();
        String phoneUsur = dto.getPhoneUsur();
        State stateUsur = dto.getStateUsur();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
        if (idUsur != null) {
            expressionsAnd.add(root.idUsur.eq(idUsur));
        }
        if (StringUtils.isNotEmpty(addressUsur)) {
            expressionsAnd.add(root.addressUsur.like("%"+addressUsur+"%"));
        }
        if (StringUtils.isNotEmpty(cellphoneUsur)) {
            expressionsAnd.add(root.cellphoneUsur.like("%"+cellphoneUsur+"%"));
        }
        if (StringUtils.isNotEmpty(cityUsur)) {
            expressionsAnd.add(root.cityUsur.like("%"+cityUsur+"%"));
        }
        if (StringUtils.isNotEmpty(cpfUsur)) {
            expressionsAnd.add(root.cpfUsur.like("%"+cpfUsur+"%"));
        }
        if (StringUtils.isNotEmpty(emailUsur)) {
            expressionsAnd.add(root.emailUsur.like("%"+emailUsur+"%"));
        }
        if (StringUtils.isNotEmpty(nameUsur)) {
            expressionsAnd.add(root.nameUsur.like("%"+nameUsur+"%"));
        }
        if (StringUtils.isNotEmpty(phoneUsur)) {
            expressionsAnd.add(root.phoneUsur.like("%"+phoneUsur+"%"));
        }
        if (stateUsur != null) {
            expressionsAnd.add(root.stateUsur.eq(stateUsur));
        }

        return addAnd(expressionsAnd);
    }

}
