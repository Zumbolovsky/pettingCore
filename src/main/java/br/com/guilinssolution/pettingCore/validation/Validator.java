package br.com.guilinssolution.pettingCore.validation;

import br.com.guilinssolution.pettingCore.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class Validator {

    public void hibernateException(BindingResult result) {
        if (result.hasErrors()) {
            log.warn("Erro detectado !");
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            throw new ApplicationException(erros, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public <T> void entityNull(T entity) {
        if (entity == null) {
            String msg = "Entidade nula !";
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_FOUND);
        }
    }

    public <T> void entityNull(Optional<T> optional) {
        if (!optional.isPresent()) {
            String msg = "Entidade nula !";
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_FOUND);
        }
    }

    public <T> void entityExist(Integer id, JpaRepository<T, Integer> repository) {
        if (id <= 0) {
            String msg = "ID menor ou igual a zero !";
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (repository.existsById(id)) {
            String msg = "ID do dado já existe !";
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }

    }

    public <T> void entityNotExist(Integer id, JpaRepository<T, Integer> repository) {
        if (id <= 0) {
            String msg = "ID menor ou igual a zero !";
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.NOT_ACCEPTABLE);
        }
        if (!repository.existsById(id)) {
            String msg = "ID do dado já existe !";
            log.warn(msg);
            throw new ApplicationException(msg, HttpStatus.CONFLICT);
        }

    }

}
