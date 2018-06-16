package br.com.guilinssolution.pettingCore.controller;

import javax.validation.Valid;

import br.com.guilinssolution.pettingCore.model.dto.custom.PostAnimalCustomDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageableDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.enums.Custom;
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
import br.com.guilinssolution.pettingCore.model.example.PostAnimalExample;
import br.com.guilinssolution.pettingCore.services.PostAnimalService;
import br.com.guilinssolution.pettingCore.validation.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/secured/post-animal")
@Api(value = "PostAnimalControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, tags = "PostAnimal Controller")
public class PostAnimalController extends GenericController {

    private final PostAnimalService service;

    private final Validator validator;

    @Autowired
    public PostAnimalController(PostAnimalService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    @ApiOperation(value = "Lista de todos dados", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ListResultExample<PostAnimalDTO> findAll(@RequestBody PostAnimalExample example,
                                                    PageExample page) {
        log.info("Listar todos os dados de Publicação Animal");
        PostAnimalDTO dto = buildDTO(example);
        return this.service.findAll(dto, null, Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista de todos dados (para cachorros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-dog", method = RequestMethod.GET)
    public ListResultExample<PostAnimalDTO> findAllDog(@RequestBody PostAnimalExample example,
                                                       PageExample page) {
        log.info("Listar todos os dados de Publicação Animal (tipo cachorro)");
        PostAnimalDTO dto = buildDTO(example);
        return this.service.findAll(dto, Species.CACHORRO, Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista dados customizados (para cachorros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-dog-custom", method = RequestMethod.GET)
    public PageableDTO findAllDogCustom(@RequestBody PostAnimalExample example,
                                        PageExample page) {
        log.info("Listar dados customizados de Publicação Animal (tipo cachorro)");
        PostAnimalDTO dto = buildDTO(example);
        ListResultExample<PostAnimalDTO> listResultExample = this.service.findAll(dto, Species.CACHORRO, Custom.CUSTOM, page);
        return buildPageableDTO(listResultExample, buildCustomList(listResultExample.getContent()));
    }

    @ApiOperation(value = "Lista de todos dados (para gatos)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-cat", method = RequestMethod.GET)
    public ListResultExample<PostAnimalDTO> findAllCat(@RequestBody PostAnimalExample example,
                                                       PageExample page) {
        log.info("Listar todos os dados de Publicação Animal (tipo gato)");
        PostAnimalDTO dto = buildDTO(example);
        return this.service.findAll(dto, Species.GATO, Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista dados customizados (para gatos)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-cat-custom", method = RequestMethod.GET)
    public PageableDTO findAllCatCustom(@RequestBody PostAnimalExample example,
                                        PageExample page) {
        log.info("Listar dados customizados de Publicação Animal (tipo gato)");
        PostAnimalDTO dto = buildDTO(example);
        ListResultExample<PostAnimalDTO> listResultExample = this.service.findAll(dto, Species.GATO, Custom.CUSTOM, page);
        return buildPageableDTO(listResultExample, buildCustomList(listResultExample.getContent()));
    }

    @ApiOperation(value = "Lista de todos dados (para pássaros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-bird", method = RequestMethod.GET)
    public ListResultExample<PostAnimalDTO> findAllBird(@RequestBody PostAnimalExample example,
                                                        PageExample page) {
        log.info("Listar todos os dados de Publicação Animal (tipo pássaro)");
        PostAnimalDTO dto = buildDTO(example);
        return this.service.findAll(dto, Species.PASSARO, Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista dados customizados (para pássaros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-bird-custom", method = RequestMethod.GET)
    public PageableDTO findAllBirdCustom(@RequestBody PostAnimalExample example,
                                         PageExample page) {
        log.info("Listar dados customizados de Publicação Animal (tipo pássaro)");
        PostAnimalDTO dto = buildDTO(example);
        ListResultExample<PostAnimalDTO> listResultExample = this.service.findAll(dto, Species.PASSARO, Custom.CUSTOM, page);
        return buildPageableDTO(listResultExample, buildCustomList(listResultExample.getContent()));
    }

    @ApiOperation(value = "Lista de todos dados (para roedores)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-rodent", method = RequestMethod.GET)
    public ListResultExample<PostAnimalDTO> findAllRodent(@RequestBody PostAnimalExample example,
                                                          PageExample page) {
        log.info("Listar todos os dados de Publicação Animal (tipo roedor)");
        PostAnimalDTO dto = buildDTO(example);
        return this.service.findAll(dto, Species.ROEDOR, Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista dados customizados (para roedores)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-rodent-custom", method = RequestMethod.GET)
    public PageableDTO findAllRodentCustom(@RequestBody PostAnimalExample example,
                                           PageExample page) {
        log.info("Listar dados customizados de Publicação Animal (tipo roedor)");
        PostAnimalDTO dto = buildDTO(example);
        ListResultExample<PostAnimalDTO> listResultExample = this.service.findAll(dto, Species.ROEDOR, Custom.CUSTOM, page);
        return buildPageableDTO(listResultExample, buildCustomList(listResultExample.getContent()));
    }

    @ApiOperation(value = "Lista de todos dados (para outros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-other", method = RequestMethod.GET)
    public ListResultExample<PostAnimalDTO> findAllOther(@RequestBody PostAnimalExample example,
                                                         PageExample page) {
        log.info("Listar todos os dados de Publicação Animal (tipo outros)");
        PostAnimalDTO dto = buildDTO(example);
        return this.service.findAll(dto, Species.OUTROS, Custom.NORMAL, page);
    }

    @ApiOperation(value = "Lista dados customizados (para outros)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-other-custom", method = RequestMethod.GET)
    public PageableDTO findAllOtherCustom(@RequestBody PostAnimalExample example,
                                          PageExample page) {
        log.info("Listar dados customizados de Publicação Animal (tipo outros)");
        PostAnimalDTO dto = buildDTO(example);
        ListResultExample<PostAnimalDTO> listResultExample = this.service.findAll(dto, Species.OUTROS, Custom.CUSTOM, page);
        return buildPageableDTO(listResultExample, buildCustomList(listResultExample.getContent()));
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all-lite", method = RequestMethod.GET)
    public ListResultExample<PostAnimalDTO> findAllLite(@RequestBody PostAnimalExample example,
                                                        PageExample page) {
        log.info("Listar todos os dados de Publicação Animal");
        PostAnimalDTO dto = buildDTO(example);
        return this.service.findAllLite(dto, page);
    }

    @ApiOperation(value = "Lista por ID de usuário", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/all/usur", method = RequestMethod.GET)
    public ListResultExample<PostAnimalDTO> listByUsur(PageExample pageExample) {
        log.info("Listando Publicações Animal por usuário");
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.service.listByUsur(Integer.parseInt(principal), pageExample);
    }

    @ApiOperation(value = "Busca dados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostAnimalDTO> findOne(@PathVariable Integer id) {
        log.info("Pesquisando dados de um Publicação Animal");
        return new ResponseEntity<>(this.service.findOne(id, Custom.NORMAL), HttpStatus.FOUND);
    }

    @ApiOperation(value = "Busca dados customizados pelo identificador", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{id}-custom", method = RequestMethod.GET)
    public ResponseEntity<PostAnimalDTO> findOneCustom(@PathVariable Integer id) {
        log.info("Pesquisando dados customizados de um Publicação Animal");
        return new ResponseEntity<>(this.service.findOne(id, Custom.CUSTOM), HttpStatus.FOUND);
    }

    @ApiOperation(value = "Cadastra dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PostAnimalDTO> save(@Valid @RequestBody PostAnimalExample example,
                                              @RequestParam Integer idUsur,
                                              BindingResult result) {
        log.info("Cadastrando dados de um Publicação Animal");
        this.validator.hibernateException(result);
        PostAnimalDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.save(dto, idUsur), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Cadastra dados no banco (usuário da sessão)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/usur", method = RequestMethod.POST)
    public ResponseEntity<PostAnimalDTO> saveSessionUser(@Valid @RequestBody PostAnimalExample example,
                                                         BindingResult result) {
        log.info("Cadastrando dados de um Publicação Animal (sessão)");
        this.validator.hibernateException(result);
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostAnimalDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.save(dto, Integer.parseInt(principal)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza dados no banco (especificando relações)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<PostAnimalDTO> update(@PathVariable Integer currentId,
                                                @Valid @RequestBody PostAnimalExample example,
                                                @RequestParam Integer idUsur,
                                                BindingResult result) {
        log.info("Atualizando dados de um Publicação Animal e suas relações");
        this.validator.hibernateException(result);
        PostAnimalDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.update(currentId, dto, idUsur), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Atualiza dados no banco (sem especificar relações)", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/quick/{currentId}", method = RequestMethod.PUT)
    public ResponseEntity<PostAnimalDTO> quickUpdate(@PathVariable Integer currentId,
                                                     @Valid @RequestBody PostAnimalExample example,
                                                     BindingResult result) {
        log.info("Atualizando dados de um Publicação Animal");
        this.validator.hibernateException(result);
        PostAnimalDTO dto = buildDTO(example);
        return new ResponseEntity<>(this.service.quickUpdate(currentId, dto), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Exclui dados no banco", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("Deletando dados de um Publicação Animal");
        this.service.delete(id);
    }

    private PostAnimalDTO buildDTO(PostAnimalExample example) {
        return PostAnimalDTO.builder()
                .idPostAnimal(null)
                .titlePostAnimal(example.getTitlePostAnimal())
                .descriptionPostAnimal(example.getDescriptionPostAnimal())
                .sizePostAnimal(example.getSizePostAnimal())
                .speciesPostAnimal(example.getSpeciesPostAnimal())
                .usurDTO(null)
                .build();
    }

    private List<PostAnimalCustomDTO> buildCustomList(List<PostAnimalDTO> content) {
        return content.stream().map(p ->
                new PostAnimalCustomDTO(p.getIdPostAnimal(),
                        p.getTitlePostAnimal(),
                        p.getDescriptionPostAnimal(),
                        p.getSizePostAnimal()))
                .collect(Collectors.toList());
    }

}
