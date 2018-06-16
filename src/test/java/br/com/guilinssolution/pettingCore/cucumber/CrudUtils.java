package br.com.guilinssolution.pettingCore.cucumber;

import br.com.guilinssolution.pettingCore.util.IntegrationTestCommon;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class CrudUtils extends IntegrationTestCommon {
    
    @Value("${local.server.port}")
    private int serverPort;
    
    @Given("^carrego os templates$")
    public void carregarTemplate() {
        FixtureFactoryLoader.loadTemplates("br.com.labtorq.consorciocore.template");

        RestAssured.port = serverPort;
        RestAssured.defaultParser = Parser.JSON;       
    }

}
