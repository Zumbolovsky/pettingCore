package br.com.guilinssolution.pettingCore.controller;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.services.ContributionService;
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
@RequestMapping(path = "/contribution")
@Api(value = "ContributionControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Contribution Controller")
public class ContributionController {

    private final ContributionService service;

    @Autowired
    public ContributionController(ContributionService service) {
        this.service = service;
    }

    @ApiOperation(value = "Lista de todos dados")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ListResultDTO<ContributionDTO> findAll(@Valid @RequestBody ContributionDTO dto, PageDTO page) {
        log.info("Listar todos os dados de Contribuição");
        return this.service.findAll(dto, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/all-lite", method = RequestMethod.GET)
    public ListResultDTO<ContributionDTO> findAllLite(@Valid @RequestBody ContributionDTO dto, PageDTO page) {
        log.info("Listar todos os dados de Contribuição");
        return this.service.findAllLite(dto, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ContributionDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de uma Contribuição");
        return ResponseEntity.ok(this.service.findOne(id));
    }

    @ApiOperation(value = "Cadastra dados no banco")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContributionDTO> save(@Valid @RequestBody ContributionDTO dto, BindingResult result) {
        log.info("Cadastrando dados de uma Contribuição");
        //validar
        return new ResponseEntity<>(this.service.save(dto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContributionDTO> update(@Valid @RequestBody ContributionDTO dto, @PathVariable Integer id, BindingResult result) {
        log.info("Atualizando dados de uma Contribuição");
        //validar
        return ResponseEntity.ok(this.service.update(id, dto));
    }

    @ApiOperation("Exclui dados no banco")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de uma Contribuição");
        this.service.delete(id);
    }

}
