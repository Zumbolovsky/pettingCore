package br.com.guilinssolution.pettingCore.services.impl;

import static br.com.guilinssolution.pettingCore.helper.SQLHelper.addAnd;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.CustomUserDetails;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.entities.QUsurEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.enums.State;
import br.com.guilinssolution.pettingCore.model.example.UsurExample;
import br.com.guilinssolution.pettingCore.repositories.ContributionRepository;
import br.com.guilinssolution.pettingCore.repositories.PostAnimalRepository;
import br.com.guilinssolution.pettingCore.repositories.PostItemRepository;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.MessageService;
import br.com.guilinssolution.pettingCore.services.StorageService;
import br.com.guilinssolution.pettingCore.services.UsurService;
import br.com.guilinssolution.pettingCore.validation.Validator;

/////////////////////////////////////////
// TODO: Implementar serviço para imagem
/////////////////////////////////////////

@Service
public class UsurServiceImpl implements UsurService {

    private final ContributionRepository contributionRepository;

    private final PostAnimalRepository postAnimalRepository;

    private final PostItemRepository postItemRepository;

    private final UsurRepository repository;
    
    private final StorageService storageService;

    private final Validator validator;

    private final MessageService message;
    
    @Value("${folder.path.usur}")
	private Path usurFileFolderPath;

    @Autowired
    public UsurServiceImpl(StorageService storageService, UsurRepository repository, ContributionRepository contributionRepository,
                           PostAnimalRepository postAnimalRepository, PostItemRepository postItemRepository, Validator validator, MessageService message) {
    	this.storageService = storageService;
        this.repository = repository;
        this.contributionRepository = contributionRepository;
        this.postAnimalRepository = postAnimalRepository;
        this.postItemRepository = postItemRepository;
        this.validator = validator;
        this.message = message;
    }

    @Override
    public ListResultDTO<UsurDTO> findAll(UsurExample example, PageDTO page) {
        BooleanExpression query = queryGeneration(example);
        Pageable pageable = PageHelper.getPage(page);

        return findAll(query, pageable, ConvertType.NORMAL);
    }

    @Override
    public ListResultDTO<UsurDTO> findAllLite(UsurExample example, PageDTO page) {
        BooleanExpression query = queryGeneration(example);
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
    public UsurDTO save(UsurDTO dto, MultipartFile file) {
        UsurEntity usurEntity = UsurAdapter.convertToEntity(dto);

        this.validator.entityExistByEmail(dto.getEmailUsur(), this.repository);
        this.validator.entityExistByEntity(usurEntity, this.repository);

        if (file != null) {
        	usurEntity.setImageUsur(this.usurFileFolderPath.resolve(usurEntity.getNameUsur()
					+ usurEntity.getCpfUsur()).toString()
					+ Objects.requireNonNull(file.getContentType()).replace("image/", "."));
        	storageService.storeFile(file, usurEntity);
		}
        
        usurEntity = this.repository.save(usurEntity);

        return UsurAdapter.convertToDTO(usurEntity);
    }

    @Override
    public UsurDTO update(Integer currentId, UsurDTO dto, MultipartFile file) {
        this.validator.entityNotExist(currentId, this.repository);

        UsurEntity vesselUsurEntity = this.repository.getOne(currentId);
        UsurEntity newUsurEntity = UsurAdapter.convertToEntity(dto);

        this.validator.entityExistByEmail(newUsurEntity.getEmailUsur(), this.repository);
        this.validator.entityExistByEntity(newUsurEntity, this.repository);
        if (file != null) {
        	storageService.storeFile(file, newUsurEntity);
		}
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

    private BooleanExpression queryGeneration(UsurExample example) {
        QUsurEntity root = QUsurEntity.usurEntity;

        String addressUsur = example.getAddressUsur();
        String cellphoneUsur = example.getCellphoneUsur();
        String cityUsur = example.getCityUsur();
        String cpfUsur = example.getCpfUsur();
        String emailUsur = example.getEmailUsur();
        String nameUsur = example.getNameUsur();
        String passwordUsur = example.getPasswordUsur();
        String phoneUsur = example.getPhoneUsur();
        State stateUsur = example.getStateUsur();

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
