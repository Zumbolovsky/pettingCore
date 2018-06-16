package br.com.guilinssolution.pettingCore.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/teste_integrado")
public class PettingCoreIT {}
