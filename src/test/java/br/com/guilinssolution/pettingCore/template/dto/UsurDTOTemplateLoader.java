package br.com.guilinssolution.pettingCore.template.dto;

import br.com.guilinssolution.pettingCore.model.dto.UsurDTO;
import br.com.guilinssolution.pettingCore.template.CommonTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class UsurDTOTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(UsurDTO.class).addTemplate(RULE_VALID, new Rule() {{
            add("idUsur", uniqueRandom(1, 2, 3));
	        add("nameUsur", random("Lorem", "ispum", "dolor"));
	        add("cpfUsur", uniqueRandom("Lorem", "ispum", "dolor"));
	        add("addressUsur", random("Lorem", "ispum", "dolor"));
	        add("cityUsur", random("Lorem", "ispum", "dolor"));
	        add("emailUsur", uniqueRandom("Lorem", "ispum", "dolor"));
	        add("passwordUsur", random("Lorem", "ispum", "dolor"));
	        add("cellphoneUsur", random("Lorem", "ispum", "dolor"));
	        add("phoneUsur", random("Lorem", "ispum", "dolor"));
        }});
    }
    
}
