package br.com.guilinssolution.pettingCore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.example.ContributionExample;
import br.com.guilinssolution.pettingCore.services.ContributionService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/contribution")
@Api(value = "ContributionControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Contribution Controller")
public class ContributionController {

    private final ContributionService service;

    private final Validator validator;

    @Autowired
    public ContributionController(ContributionService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @ApiOperation(value = "Lista de todos dados")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ListResultDTO<ContributionDTO> findAll(ContributionExample example, PageDTO page) {
        log.info("Listar todos os dados de Contribuição");
        return this.service.findAll(example, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/all-lite", method = RequestMethod.GET)
    public ListResultDTO<ContributionDTO> findAllLite(ContributionExample example, PageDTO page) {
        log.info("Listar todos os dados de Contribuição");
        return this.service.findAllLite(example, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ContributionDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de uma Contribuição");
        return ResponseEntity.ok(this.service.findOne(id));
    }

    @ApiOperation(value = "Cadastra dados no banco")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContributionDTO> save(@Valid @RequestBody ContributionDTO dto,
                                                @RequestParam(required = false) Integer idPostAnimal,
                                                @RequestParam(required = false) Integer idPostItem,
                                                @RequestParam Integer idUsurRequest,
                                                @RequestParam(required = false) Integer idUsurDonator,
                                                BindingResult result) {
        log.info("Cadastrando dados de uma Contribuição");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.save(dto, idPostAnimal, idPostItem, idUsurRequest, idUsurDonator),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco (especificando relações)")
    @RequestMapping(value = "/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<ContributionDTO> update(@PathVariable Integer currentId,
                                                  @RequestParam(required = false) Integer idPostAnimal,
                                                  @RequestParam(required = false) Integer idPostItem,
                                                  @RequestParam Integer idUsurRequest,
                                                  @RequestParam(required = false) Integer idUsurDonator,
                                                  @Valid @RequestBody ContributionDTO dto,
                                                  BindingResult result) {
        log.info("Atualizando dados de uma Contribuição e suas relações");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.update(currentId, dto, idPostAnimal, idPostItem, idUsurRequest, idUsurDonator),
                HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Atualiza dados no banco (sem especificar relações)")
    @RequestMapping(value = "/quick/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<ContributionDTO> quickUpdate(@PathVariable Integer currentId,
                                                       @Valid @RequestBody ContributionDTO dto,
                                                       BindingResult result) {
        log.info("Atualizando dados de uma Contribuição");
        this.validator.hibernateException(result);
        return new ResponseEntity<>(this.service.quickUpdate(currentId, dto), HttpStatus.ACCEPTED);
    }

    @ApiOperation("Exclui dados no banco")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de uma Contribuição");
        this.service.delete(id);
    }

    @ApiOperation("Lista por ID de usuário contribuinte")
    @RequestMapping(value = "/all/donator/{idUsur}", method = RequestMethod.GET)
    public ListResultDTO<ContributionDTO> listByDonator(@PathVariable Integer idUsur, PageDTO pageDTO) {
        log.info("Listando Contribuições por usuário contribuinte");
        return this.service.listByDonator(idUsur, pageDTO);
    }
}
