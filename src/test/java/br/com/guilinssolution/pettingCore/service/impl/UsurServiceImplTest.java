package br.com.guilinssolution.pettingCore.service.impl;

import br.com.guilinssolution.pettingCore.helper.FixtureFactoryHelper;
import br.com.guilinssolution.pettingCore.model.CustomUserDetails;
import br.com.guilinssolution.pettingCore.model.adapter.UsurAdapter;
import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.impl.UsurServiceImpl;
import br.com.guilinssolution.pettingCore.template.dto.UsurDTOTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class UsurServiceImplTest {

//    private final ListResultDTO<UsurDTO> listResultAll = new ListResultDTO<>();
//    private final List<UsurDTO> listAll = new ArrayList<>();
//    private final ListResultDTO<UsurDTO> listResultAllLite = new ListResultDTO<>();
//    private final List<UsurDTO> DTOMass = new ArrayList<>();
//    private UserDetails securityMass;
//
//    @InjectMocks
//    private UsurServiceImpl service;
//
//    @Mock
//    private UsurRepository repository;
//
//    @Mock
//    private PageDTO pageDTO;
//
//    @BeforeClass
//    public static void loadTemplates() {
//        FixtureFactoryLoader.loadTemplates(FixtureFactoryHelper.FIXTURE_FACTORY_BASE_PACKAGE);
//    }
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        this.DTOMass.add(Fixture.from(UsurDTO.class).gimme(UsurDTOTemplateLoader.RULE_VALID));
//        this.DTOMass.add(Fixture.from(UsurDTO.class).gimme(UsurDTOTemplateLoader.RULE_VALID));
//        this.DTOMass.add(Fixture.from(UsurDTO.class).gimme(UsurDTOTemplateLoader.RULE_VALID));
//
//        this.listAll.addAll(this.DTOMass);
//        this.listResultAll.setContent(this.listAll);
//
//        this.listResultAllLite.setContent(this.listAll.stream()
//                .map(item -> UsurDTO.builder().idUsur(item.getIdUsur()).build()).collect(Collectors.toList()));
//
//        this.securityMass = new CustomUserDetails(UsurAdapter.convertToEntity(DTOMass.get(0)));
//
//        doReturn().when(this.repository).
//    }

}
