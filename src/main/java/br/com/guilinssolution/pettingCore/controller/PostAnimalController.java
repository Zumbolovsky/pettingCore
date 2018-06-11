package br.com.guilinssolution.pettingCore.controller;

import javax.validation.Valid;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.example.PostAnimalExample;
import br.com.guilinssolution.pettingCore.services.PostAnimalService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
@Api(value = "PostAnimalControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "PostAnimal Controller")
public class PostAnimalController {

    private final PostAnimalService service;

    private final Validator validator;

    @Autowired
    public PostAnimalController(PostAnimalService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @ApiOperation(value = "Lista de todos dados", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal/all", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAll(@RequestBody PostAnimalExample example,
                                                PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal");
        return this.service.findAll(example, null, page);
    }

    @ApiOperation(value = "Lista por ID de usuário", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal/all/usur", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> listByUsur(PageDTO pageDTO) {
        log.info("Listando Publicações Animal por usuário");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.service.listByUsur(Integer.parseInt(principal), pageDTO);
    }

    @ApiOperation(value = "Lista de todos dados (para cachorros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/post-animal/all-dog", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAllDog(@RequestBody PostAnimalExample example,
                                                   PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal (tipo cachorro)");
        return this.service.findAll(example, Species.CACHORRO, page);
    }

    @ApiOperation(value = "Lista de todos dados (para gatos)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/post-animal/all-cat", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAllCat(@RequestBody PostAnimalExample example,
                                                   PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal (tipo gato)");
        return this.service.findAll(example, Species.GATO, page);
    }

    @ApiOperation(value = "Lista de todos dados (para pássaros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/post-animal/all-bird", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAllBird(@RequestBody PostAnimalExample example,
                                                    PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal (tipo pássaro)");
        return this.service.findAll(example, Species.PASSARO, page);
    }

    @ApiOperation(value = "Lista de todos dados (para roedores)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/post-animal/all-rodent", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAllRodent(@RequestBody PostAnimalExample example,
                                                      PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal (tipo roedor)");
        return this.service.findAll(example, Species.ROEDOR, page);
    }

    @ApiOperation(value = "Lista de todos dados (para outros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/post-animal/all-other", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAllOther(@RequestBody PostAnimalExample example,
                                                     PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal (tipo outros)");
        return this.service.findAll(example, Species.OUTROS, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal/all-lite", method = RequestMethod.GET)
    public ListResultDTO<PostAnimalDTO> findAllLite(@RequestBody PostAnimalExample example,
                                                    PageDTO page) {
        log.info("Listar todos os dados de Publicação Animal");
        return this.service.findAllLite(example, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostAnimalDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de um Publicação Animal");
        return new ResponseEntity<>(this.service.findOne(id), HttpStatus.FOUND);
    }

    @ApiOperation(value = "Cadastra dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal",method = RequestMethod.POST)
    public ResponseEntity<PostAnimalDTO> save(@Valid @RequestBody PostAnimalDTO dto,
                                              @RequestParam Integer idUsur,
                                              BindingResult result) {
        log.info("Cadastrando dados de um Publicação Animal");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.save(dto, idUsur), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Cadastra dados no banco (usuário da sessão)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal/usur", method = RequestMethod.POST)
    public ResponseEntity<PostAnimalDTO> saveSessionUser(@Valid @RequestBody PostAnimalDTO dto,
                                                         BindingResult result) {
        log.info("Cadastrando dados de um Publicação Animal (sessão)");
        this.validator.hibernateException(result);
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(this.service.save(dto, Integer.parseInt(principal)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco (especificando relações)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<PostAnimalDTO> update(@PathVariable Integer currentId,
                                                @Valid @RequestBody PostAnimalDTO dto,
                                                @RequestParam Integer idUsur,
                                                BindingResult result) {
        log.info("Atualizando dados de um Publicação Animal e suas relações");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.update(currentId, dto, idUsur), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Atualiza dados no banco (sem especificar relações)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal/quick/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<PostAnimalDTO> quickUpdate(@PathVariable Integer currentId,
                                                     @Valid @RequestBody PostAnimalDTO dto,
                                                     BindingResult result) {
        log.info("Atualizando dados de um Publicação Animal");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.quickUpdate(currentId, dto), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Exclui dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/post-animal/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de um Publicação Animal");
        this.service.delete(id);
    }

}
