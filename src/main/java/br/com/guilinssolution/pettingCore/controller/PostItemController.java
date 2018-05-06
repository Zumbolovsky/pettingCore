package br.com.guilinssolution.pettingCore.controller;

import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.services.PostItemService;
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
@RequestMapping(path = "/post-item")
@Api(value = "PostItemControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "PostItem Controller")
public class PostItemController {

    private final PostItemService service;

    @Autowired
    public PostItemController(PostItemService service) {
        this.service = service;
    }

    @ApiOperation(value = "Lista de todos dados")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> findAll(@Valid @RequestBody PostItemDTO dto, PageDTO page) {
        log.info("Listar todos os dados de Publicação Item");
        return this.service.findAll(dto, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/all-lite", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> findAllLite(@Valid @RequestBody PostItemDTO dto, PageDTO page) {
        log.info("Listar todos os dados de Publicação Item");
        return this.service.findAllLite(dto, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostItemDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de um Publicação Item");
        return ResponseEntity.ok(this.service.findOne(id));
    }

    @ApiOperation(value = "Cadastra dados no banco")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PostItemDTO> save(@Valid @RequestBody PostItemDTO dto, BindingResult result) {
        log.info("Cadastrando dados de um Publicação Item");
        //validar
        return new ResponseEntity<>(this.service.save(dto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PostItemDTO> update(@Valid @RequestBody PostItemDTO dto, @PathVariable Integer id, BindingResult result) {
        log.info("Atualizando dados de um Publicação Item");
        //validar
        return ResponseEntity.ok(this.service.update(id, dto));
    }

    @ApiOperation("Exclui dados no banco")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de um Publicação Item");
        this.service.delete(id);
    }

}
