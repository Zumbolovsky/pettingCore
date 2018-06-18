package br.com.guilinssolution.pettingCore.controller;

import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.dto.custom.UsurCustomDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.example.UsurExample;
import br.com.guilinssolution.pettingCore.services.UsurService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @ApiOperation(value = "Lista de todos dados", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/usur/all", method = RequestMethod.GET)
    public ListResultExample<UsurDTO> findAll(@RequestBody UsurExample example,
                                              PageExample page) {
        log.info("Listar todos os dados de Usuário");
        UsurDTO dto = buildDTO(example);
        return this.service.findAll(dto, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/usur/all-lite", method = RequestMethod.GET)
    public ListResultExample<UsurDTO> findAllLite(@RequestBody UsurExample example,
                                                  PageExample page) {
        log.info("Listar todos os dados de Usuário");
        UsurDTO dto = buildDTO(example);
        return this.service.findAllLite(dto, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/usur/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsurDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de um Usuário");
        return new ResponseEntity<>(this.service.findOne(id, Custom.NORMAL), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca dados customizados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/usur", method = RequestMethod.GET)
    public ResponseEntity<UsurCustomDTO> findOneCustom() {
        log.info("Pesquisando dados customizados de um Usuário");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsurDTO dto = this.service.findOne(Integer.parseInt(principal), Custom.CUSTOM);
        return new ResponseEntity<>(buildCustomDTO(dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Cadastra dados no banco")
    @RequestMapping(value = "/usur", method = RequestMethod.POST)
    public ResponseEntity<UsurDTO> save(@Valid @RequestBody  UsurExample example,
                                        BindingResult result) {
        log.info("Cadastrando dados de um Usuário");
        this.validator.hibernateException(result);
        UsurDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.save(dto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/usur/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<UsurDTO> update(@PathVariable Integer currentId,
                                          @Valid @RequestBody UsurExample example,
                                          BindingResult result) {
        log.info("Atualizando dados de um Usuário");
        this.validator.hibernateException(result);
        UsurDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.update(currentId, dto), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Exclui dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/secured/usur/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de um Usuário");
        this.service.delete(id);
    }

    private UsurDTO buildDTO(UsurExample example) {
        return UsurDTO.builder()
                .idUsur(null)
                .nameUsur(example.getNameUsur())
                .cpfUsur(example.getCpfUsur())
                .emailUsur(example.getEmailUsur())
                .passwordUsur(example.getPasswordUsur())
                .phoneUsur(example.getPhoneUsur())
                .cellphoneUsur(example.getCellphoneUsur())
                .cityUsur(example.getCityUsur())
                .addressUsur(example.getAddressUsur())
                .stateUsur(example.getStateUsur())
                .build();
    }

    private UsurCustomDTO buildCustomDTO(UsurDTO dto) {
        return new UsurCustomDTO(dto.getNameUsur(), dto.getEmailUsur(), dto.getPhoneUsur());
    }

}

