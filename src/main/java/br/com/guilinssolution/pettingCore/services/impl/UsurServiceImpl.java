package br.com.guilinssolution.pettingCore.services.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.CustomUserDetails;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.*;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.entities.QUsurEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.State;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.MessageService;
import br.com.guilinssolution.pettingCore.services.UsurService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final MessageService message;

    @Autowired
    public UsurServiceImpl(UsurRepository repository, Validator validator, MessageService message) {
        this.repository = repository;
        this.validator = validator;
        this.message = message;
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
        Pageable pageable = PageHelper.getPageLite(page);

        return findAll(query, pageable, ConvertType.LITE);
    }

    @Override
    public UsurDTO findOne(Integer id, Custom custom) {
        UsurEntity usurEntity = this.repository.getOne(id);

        this.validator.entityNull(usurEntity);
        this.validator.entityNotExist(id, this.repository);

        if (custom.equals(Custom.CUSTOM)) {
            usurEntity = UsurEntity.builder()
                    .nameUsur(usurEntity.getNameUsur())
                    .emailUsur(usurEntity.getEmailUsur())
                    .phoneUsur(usurEntity.getPhoneUsur())
                    .build();
        }
        return UsurAdapter.convertToDTO(usurEntity);
    }

    @Override
    public UsurDTO save(UsurDTO dto) {
        UsurEntity usurEntity = UsurAdapter.convertToEntity(dto);

        this.validator.entityExistByEmail(dto.getEmailUsur(), this.repository);
        this.validator.entityExistByEntity(usurEntity, this.repository);

        usurEntity = this.repository.save(usurEntity);

        return UsurAdapter.convertToDTO(usurEntity);
    }

    @Override
    public UsurDTO update(Integer currentId, UsurDTO dto) {
        this.validator.entityNotExist(currentId, this.repository);

        UsurEntity vesselUsurEntity = this.repository.getOne(currentId);
        UsurEntity newUsurEntity = UsurAdapter.convertToEntity(dto);

        this.validator.entityExistByEmail(newUsurEntity.getEmailUsur(), this.repository);
        this.validator.entityExistByEntity(newUsurEntity, this.repository);
        vesselUsurEntity.update(newUsurEntity);

        newUsurEntity = this.repository.save(vesselUsurEntity);
        return UsurAdapter.convertToDTO(newUsurEntity);
    }

    @Override
    public void delete(Integer id) {
        UsurEntity entity = this.repository.getOne(id);

        this.validator.entityNotExist(id, this.repository);

        this.repository.delete(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsurEntity usurEntity = this.repository.findByEmail(email);

        if (usurEntity == null) {
            throw new UsernameNotFoundException(this.message.getMessage("username.not-found"));
        }
        return new CustomUserDetails(usurEntity);
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

        String addressUsur = dto.getAddressUsur();
        String cellphoneUsur = dto.getCellphoneUsur();
        String cityUsur = dto.getCityUsur();
        String cpfUsur = dto.getCpfUsur();
        String emailUsur = dto.getEmailUsur();
        String nameUsur = dto.getNameUsur();
        String passwordUsur = dto.getPasswordUsur();
        String phoneUsur = dto.getPhoneUsur();
        State stateUsur = dto.getStateUsur();

        List<BooleanExpression> expressionsAnd = new ArrayList<>();
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
        if (StringUtils.isNotEmpty(passwordUsur)) {
            expressionsAnd.add(root.passwordUsur.like("%" + passwordUsur + "%"));
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
