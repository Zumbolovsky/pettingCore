package br.com.guilinssolution.pettingCore.controller;

import br.com.guilinssolution.pettingCore.model.dto.custom.PostItemCustomDTO;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
import br.com.guilinssolution.pettingCore.model.enums.Type;
import br.com.guilinssolution.pettingCore.model.example.PostItemExample;
import br.com.guilinssolution.pettingCore.services.PostItemService;
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
@RequestMapping("/secured/post-item")
@Api(value = "PostItemControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "PostItem Controller")
public class PostItemController {

    private final PostItemService service;

    private final Validator validator;

    @Autowired
    public PostItemController(PostItemService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @ApiOperation(value = "Lista de todos dados", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> findAll(PostItemExample example,
                                              PageDTO page) {
        log.info("Listar todos os dados de Publicação Item");
        PostItemDTO dto = buildDTO(example);
        return this.service.findAll(dto, example.getTypePostItem(), Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista de todos dados (para remédios)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-medicine", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> findAllMedicine(PostItemExample example,
                                                      PageDTO page) {
        log.info("Listar todos os dados de Publicação Item");
        PostItemDTO dto = buildDTO(example);
        return this.service.findAll(dto, Type.REMEDIO, Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista de dados customizados (para remédios)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-medicine-custom", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> findAllMedicineCustom(PostItemExample example,
                                                            PageDTO page) {
        log.info("Listar dados customizados de Publicação Item");
        PostItemDTO dto = buildDTO(example);
        return this.service.findAll(dto, Type.REMEDIO, Custom.CUSTOM, page);
    }

    @ApiOperation(value = "Lista de todos dados (para produtos)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-product", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> findAllProduct(PostItemExample example,
                                                     PageDTO page) {
        log.info("Listar todos os dados de Publicação Item");
        PostItemDTO dto = buildDTO(example);
        return this.service.findAll(dto, Type.PRODUTO, Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista de dados customizados (para produtos)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-product-custom", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> findAllProductCustom(PostItemExample example,
                                                                 PageDTO page) {
        log.info("Listar dados customizados de Publicação Item");
        PostItemDTO dto = buildDTO(example);
        return this.service.findAll(dto, Type.PRODUTO, Custom.CUSTOM, page);
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-lite", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> findAllLite(@RequestBody PostItemExample example,
                                                  PageDTO page) {
        log.info("Listar todos os dados de Publicação Item");
        PostItemDTO dto = buildDTO(example);
        return this.service.findAllLite(dto, page);
    }

    @ApiOperation(value = "Lista por ID de usuário", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all/usur", method = RequestMethod.GET)
    public ListResultDTO<PostItemDTO> listByUsur(PageDTO pageDTO) {
        log.info("Listando Publicações Item por usuário");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.service.listByUsur(Integer.parseInt(principal), pageDTO);
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostItemDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de um Publicação Item");
        return ResponseEntity.ok(this.service.findOne(id));
    }

    @ApiOperation(value = "Cadastra dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PostItemDTO> save(@Valid @RequestBody PostItemExample example,
                                            @RequestParam Integer idUsur,
                                            BindingResult result) {
        log.info("Cadastrando dados de um Publicação Item");
        this.validator.hibernateException(result);
        PostItemDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.save(dto, idUsur), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Cadastra dados no banco (usuário da sessão)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/usur", method = RequestMethod.POST)
    public ResponseEntity<PostItemDTO> saveSessionUser(@Valid @RequestBody PostItemExample example,
                                                       BindingResult result) {
        log.info("Cadastrando dados de um Publicação Item (sessão)");
        this.validator.hibernateException(result);
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostItemDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.save(dto, Integer.parseInt(principal)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco (especificando relações)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<PostItemDTO> update(@PathVariable Integer currentId,
                                              @Valid @RequestBody PostItemExample example,
                                              @RequestParam Integer idUsur,
                                              BindingResult result) {
        log.info("Atualizando dados de um Publicação Item e suas relações");
        this.validator.hibernateException(result);
        PostItemDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.update(currentId, dto, idUsur), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Atualiza dados no banco (sem especificar relações)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/quick/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<PostItemDTO> quickUpdate(@PathVariable Integer currentId,
                                                   @Valid @RequestBody PostItemExample example,
                                                   BindingResult result) {
        log.info("Atualizando dados de um Publicação Item");
        this.validator.hibernateException(result);
        PostItemDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.quickUpdate(currentId, dto), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Exclui dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de um Publicação Item");
        this.service.delete(id);
    }

    private PostItemDTO buildDTO(PostItemExample example) {
        return PostItemDTO.builder()
                .idPostItem(null)
                .titlePostItem(example.getTitlePostItem())
                .descriptionPostItem(example.getDescriptionPostItem())
                .typePostItem(example.getTypePostItem())
                .speciesPostItem(example.getSpeciesPostItem())
                .usurDTO(null)
                .build();
    }

}
