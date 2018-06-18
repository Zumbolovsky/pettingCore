package br.com.guilinssolution.pettingCore.controller;

import javax.validation.Valid;

import br.com.guilinssolution.pettingCore.model.dto.custom.ContributionCustomDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageableDTO;
import br.com.guilinssolution.pettingCore.model.enums.Kind;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.ContributionExample;
import br.com.guilinssolution.pettingCore.services.ContributionService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/secured/contribution")
@Api(value = "ContributionControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "Contribution Controller")
public class ContributionController extends GenericController {

    private final ContributionService service;

    private final Validator validator;

    @Autowired
    public ContributionController(ContributionService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @ApiOperation(value = "Lista de todos dados", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ListResultExample<ContributionDTO> findAll(@RequestBody ContributionExample example,
                                                      PageExample page) {
        log.info("Listar todos os dados de Contribuição");
        ContributionDTO dto = buildDTO(example);
        return this.service.findAll(dto, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-lite", method = RequestMethod.GET)
    public ListResultExample<ContributionDTO> findAllLite(@RequestBody ContributionExample example,
                                                          PageExample page) {
        log.info("Listar todos os dados de Contribuição");
        ContributionDTO dto = buildDTO(example);
        return this.service.findAllLite(dto, page);
    }

    @ApiOperation(value = "Lista por ID de usuário contribuinte", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all/donator", method = RequestMethod.GET)
    public ListResultExample<ContributionDTO> listByDonator(PageExample pageExample) {
        log.info("Listando Contribuições por usuário contribuinte");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.service.listByDonator(Integer.parseInt(principal), pageExample, null);
    }

    @ApiOperation(value = "Lista animais por ID de usuário contribuinte", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-animal/donator", method = RequestMethod.GET)
    public ListResultExample<ContributionDTO> listAnimalByDonator(PageExample pageExample) {
        log.info("Listando Publicações Animal Contribuições por usuário contribuinte");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.service.listByDonator(Integer.parseInt(principal), pageExample, Kind.ANIMAL);
    }

    @ApiOperation(value = "Lista itens por ID de usuário contribuinte", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-item/donator", method = RequestMethod.GET)
    public ListResultExample<ContributionDTO> listItemByDonator(PageExample pageExample) {
        log.info("Listando Publicações Item Contribuições por usuário contribuinte");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.service.listByDonator(Integer.parseInt(principal), pageExample, Kind.ITEM);
    }

    @ApiOperation(value = "Lista por ID de usuário contribuinte customizada", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all/donator-custom", method = RequestMethod.GET)
    public PageableDTO listByDonatorCustom(PageExample pageExample) {
        log.info("Listando Contribuições customizadas por usuário contribuinte");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ListResultExample<ContributionDTO> listResultExample = this.service.listByDonator(Integer.parseInt(principal), pageExample, null);
        return buildPageableDTO(listResultExample, buildCustomList(listResultExample.getContent()));
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ContributionDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de uma Contribuição");
        return ResponseEntity.ok(this.service.findOne(id));
    }

    @ApiOperation(value = "Cadastra dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContributionDTO> save(@Valid @RequestBody ContributionExample example,
                                                @RequestParam(required = false) Integer idPostAnimal,
                                                @RequestParam(required = false) Integer idPostItem,
                                                @RequestParam Integer idUsurRequest,
                                                @RequestParam Integer idUsurDonator,
                                                BindingResult result) {
        log.info("Cadastrando dados de uma Contribuição");
        this.validator.hibernateException(result);
        ContributionDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.save(dto, idPostAnimal, idPostItem, idUsurRequest, idUsurDonator),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Cadastra dados no banco (usuário da sessão)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/usur", method = RequestMethod.POST)
    public ResponseEntity<ContributionDTO> saveSessionUser(@Valid @RequestBody ContributionExample example,
                                                           @RequestParam(required = false) Integer idPostAnimal,
                                                           @RequestParam(required = false) Integer idPostItem,
                                                           @RequestParam Integer idUsurRequest,
                                                           BindingResult result) {
        log.info("Cadastrando dados de uma Contribuição (sessão)");
        this.validator.hibernateException(result);
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ContributionDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.save(dto, idPostAnimal, idPostItem, idUsurRequest, Integer.parseInt(principal)),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco (especificando relações)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<ContributionDTO> update(@PathVariable Integer currentId,
                                                  @RequestParam(required = false) Integer idPostAnimal,
                                                  @RequestParam(required = false) Integer idPostItem,
                                                  @RequestParam Integer idUsurRequest,
                                                  @RequestParam Integer idUsurDonator,
                                                  @Valid @RequestBody ContributionExample example,
                                                  BindingResult result) {
        log.info("Atualizando dados de uma Contribuição e suas relações");
        this.validator.hibernateException(result);
        ContributionDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.update(currentId, dto, idPostAnimal, idPostItem, idUsurRequest, idUsurDonator),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza dados no banco (sem especificar relações)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/quick/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<ContributionDTO> quickUpdate(@PathVariable Integer currentId,
                                                       @Valid @RequestBody ContributionExample example,
                                                       BindingResult result) {
        log.info("Atualizando dados de uma Contribuição");
        this.validator.hibernateException(result);
        ContributionDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.quickUpdate(currentId, dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Exclui dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de uma Contribuição");
        this.service.delete(id);
    }

    private ContributionDTO buildDTO(ContributionExample example) {
        return ContributionDTO.builder()
                .idContribution(null)
                .descriptionContribution(example.getDescriptionContribution())
                .postAnimalDTO(null)
                .postItemDTO(null)
                .usurDTOByIdRequest(null)
                .usurDTOByIdDonator(null)
                .build();
    }

    private List<ContributionCustomDTO> buildCustomList(List<ContributionDTO> content) {
        return content.stream().map(c ->
                new ContributionCustomDTO(c.getIdContribution(),
                c.getPostAnimalDTO() == null ?
                        c.getPostItemDTO().getIdPostItem() :
                        c.getPostAnimalDTO().getIdPostAnimal(),
                c.getPostAnimalDTO() == null ?
                        c.getPostItemDTO().getTitlePostItem() :
                        c.getPostAnimalDTO().getTitlePostAnimal(),
                c.getPostAnimalDTO() == null ?
                        c.getPostItemDTO().getDescriptionPostItem() :
                        c.getPostAnimalDTO().getDescriptionPostAnimal(),
                c.getPostAnimalDTO() == null ?
                        Kind.ITEM.name() :
                        Kind.ANIMAL.name()))
                .collect(Collectors.toList());
    }

}
