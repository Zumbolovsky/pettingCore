package br.com.guilinssolution.pettingCore.controller;

import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.example.UsurExample;
import br.com.guilinssolution.pettingCore.services.UsurService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiOperation(value = "Cadastra dados no banco")
    @RequestMapping(value = "/usur", method = RequestMethod.POST)
    public ResponseEntity<UsurDTO> save(@Valid @RequestBody UsurDTO dto,
                                        BindingResult result) {
        log.info("Cadastrando dados de um Usuário");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.save(dto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco")
    @RequestMapping(value = "/secured/usur/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<UsurDTO> update(@PathVariable Integer currentId,
                                          @Valid @RequestBody UsurDTO dto,
                                          BindingResult result) {
        log.info("Atualizando dados de um Usuário");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.update(currentId, dto), HttpStatus.ACCEPTED);
    }

    @ApiOperation("Exclui dados no banco")
    @RequestMapping(value = "/secured/usur/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de um Usuário");
        this.service.delete(id);
    }

}

