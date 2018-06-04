package br.com.guilinssolution.pettingCore.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.entities.*;
import br.com.guilinssolution.pettingCore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.com.guilinssolution.pettingCore.exception.ApplicationException;
import br.com.guilinssolution.pettingCore.services.MessageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Validator {

    private final MessageService message;

    @Autowired
    public Validator(MessageService message) {
        this.message = message;
    }

    public void hibernateException(BindingResult result) {
        if (result.hasErrors()) {
            log.warn("Erro detectado !");
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            String msg = message.getMessage("error.list")
                    + erros.toString().substring(1, erros.toString().length() - 1);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public <T> void entityNull(T entity) {
        if (entity == null) {
            String msg = message.getMessage("entity.bad-request");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.BAD_REQUEST);
        }
    }

    public <T> void entityExist(Integer id, JpaRepository<T, Integer> repository) {
        if (id <= 0) {
            String msg = message.getMessage("id.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsById(id)) {
            String msg = message.getMessage("id.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntitySave(AnimalEntity entity, AnimalRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntitySave(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntityUpdate(AnimalEntity entity, AnimalRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntityUpdate(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntitySave(ContributionEntity entity, ContributionRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntitySave(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntityUpdate(ContributionEntity entity, ContributionRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntityUpdate(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntitySave(PostAnimalEntity entity, PostAnimalRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntitySave(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntityUpdate(PostAnimalEntity entity, PostAnimalRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntityUpdate(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntitySave(PostItemEntity entity, PostItemRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntitySave(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntityUpdate(PostItemEntity entity, PostItemRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntityUpdate(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntitySave(UsurEntity entity, UsurRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntitySave(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEntityUpdate(UsurEntity entity, UsurRepository repository) {
        if (entity == null) {
            String msg = message.getMessage("entity.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEntityUpdate(entity)) {
            String msg = message.getMessage("entity.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEmailSave(String email, UsurRepository repository) {
        if (email == null) {
            String msg = message.getMessage("email.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEmailSave(email)) {
            String msg = message.getMessage("email.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public void entityExistByEmailUpdate(String email, UsurRepository repository) {
        if (email == null) {
            String msg = message.getMessage("email.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsByEmailUpdate(email)) {
            String msg = message.getMessage("email.conflict");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }
    }

    public <T> void entityNotExist(Integer id, JpaRepository<T, Integer> repository) {
        if (id == null) {
            String msg = message.getMessage("id.bad-request");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.BAD_REQUEST);
        }
        if (id <= 0) {
            String msg = message.getMessage("id.not-acceptable");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (!repository.existsById(id)) {
            String msg = message.getMessage("id.not-found");
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_FOUND);
        }
    }
}
