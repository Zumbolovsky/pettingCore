package br.com.guilinssolution.pettingCore.cucumber;

import br.com.guilinssolution.pettingCore.util.IntegrationTestCommon;
import br.com.six2six.fixturefactory.Fixture;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

@ContextConfiguration
public class CriarBaseSteps extends IntegrationTestCommon {

    @When("^crio a base de dados$")
	public void crioBase() {

    }

}
