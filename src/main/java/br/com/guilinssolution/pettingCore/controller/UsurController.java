package br.com.guilinssolution.pettingCore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.example.UsurExample;
import br.com.guilinssolution.pettingCore.services.UsurService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(value = "UsurControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Usur Controller")
public class UsurController {

    private final UsurService service;

    private final Validator validator;

    @Autowired
    public UsurController(UsurService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @ApiOperation(value = "Lista de todos dados")
    @RequestMapping(value = "/secured/usur/all", method = RequestMethod.GET)
    public ListResultDTO<UsurDTO> findAll(UsurExample example, PageDTO page) {
        log.info("Listar todos os dados de Usuário");
        return this.service.findAll(example, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/secured/usur/all-lite", method = RequestMethod.GET)
    public ListResultDTO<UsurDTO> findAllLite(UsurExample example, PageDTO page) {
        log.info("Listar todos os dados de Usuário");
        return this.service.findAllLite(example, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/secured/usur/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsurDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de um Usuário");
        return new ResponseEntity<>(this.service.findOne(id), HttpStatus.FOUND);
    }

    //todo: testar
    @ApiOperation(value = "Cadastra dados no banco")
    @RequestMapping(value = "/usur", method = RequestMethod.POST)
    public ResponseEntity<UsurDTO> save(@Valid UsurDTO dto, MultipartFile file,
                                        BindingResult result) {
        log.info("Cadastrando dados de um Usuário");
        this.validator.fileTypeValidation(file);
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.save(dto, file), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco")
    @RequestMapping(value = "/secured/usur/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<UsurDTO> update(Integer currentId, @Valid UsurDTO dto,
                                          MultipartFile file, BindingResult result) {
        log.info("Atualizando dados de um Usuário");
        this.validator.fileTypeValidation(file);
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.update(currentId, dto, file), HttpStatus.ACCEPTED);
    }

    @ApiOperation("Exclui dados no banco")
    @RequestMapping(value = "/secured/usur/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de um Usuário");
        this.service.delete(id);
    }
    
}

