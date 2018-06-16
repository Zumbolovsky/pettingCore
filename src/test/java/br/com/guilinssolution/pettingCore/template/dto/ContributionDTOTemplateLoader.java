package br.com.guilinssolution.pettingCore.template.dto;

import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.template.CommonTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class ContributionDTOTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(ContributionDTO.class).addTemplate(RULE_VALID, new Rule() {{
            add("idContribution", uniqueRandom(1, 2, 3));
            add("descriptionContribution", random("Lorem", "ispum", "dolor"));
        }});
    }

}
