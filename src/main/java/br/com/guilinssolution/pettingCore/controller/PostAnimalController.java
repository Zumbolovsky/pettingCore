package br.com.guilinssolution.pettingCore.controller;

import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.example.PostAnimalExample;
import br.com.guilinssolution.pettingCore.services.PostAnimalService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/post-animal")
@Api(value = "PostAnimalControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "PostAnimal Controller")
public class PostAnimalController {

    private final PostAnimalService service;

    private final Validator validator;

    @Autowired
    public PostAnimalController(PostAnimalService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @ApiOperation(value = "Lista de todos dados")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAll(PostAnimalExample example, PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal");
        return this.service.findAll(example, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/all-lite", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAllLite(PostAnimalExample example, PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal");
        return this.service.findAllLite(example, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostAnimalDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de um Publicação Animal");
        return new ResponseEntity<>(this.service.findOne(id), HttpStatus.FOUND);
    }

    @ApiOperation(value = "Cadastra dados no banco")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PostAnimalDTO> save(@Valid @RequestBody PostAnimalDTO dto,
                                              @RequestParam Integer idAnimal,
                                              @RequestParam Integer idUsur,
                                              BindingResult result) {
        log.info("Cadastrando dados de um Publicação Animal");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.save(dto, idAnimal, idUsur), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco (especificando relações)")
    @RequestMapping(value = "/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<PostAnimalDTO> update(@PathVariable Integer currentId,
                                                @Valid @RequestBody PostAnimalDTO dto,
                                                @RequestParam Integer idAnimal,
                                                @RequestParam Integer idUsur,
                                                BindingResult result) {
        log.info("Atualizando dados de um Publicação Animal e suas relações");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.update(currentId, dto, idAnimal, idUsur), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Atualiza dados no banco (sem especificar relações)")
    @RequestMapping(value = "/quick/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<PostAnimalDTO> quickUpdate(@PathVariable Integer currentId,
                                                     @Valid @RequestBody PostAnimalDTO dto,
                                                     BindingResult result) {
        log.info("Atualizando dados de um Publicação Animal");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.quickUpdate(currentId, dto), HttpStatus.ACCEPTED);
    }

    @ApiOperation("Exclui dados no banco")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de um Publicação Animal");
        this.service.delete(id);
    }

}
