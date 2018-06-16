package br.com.guilinssolution.pettingCore.template.dto;

import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.model.enums.Type;
import br.com.guilinssolution.pettingCore.template.CommonTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class PostItemDTOTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(PostItemDTO.class).addTemplate(RULE_VALID, new Rule() {{
            add("idPostItem", uniqueRandom(1, 2, 3));
            add("titlePostItem", random("Lorem", "ispum", "dolor"));
            add("descriptionPostItem", random("Lorem", "ispum", "dolor"));
            add("typePostItem", random(Type.class));
            add("speciesPostItem", random(Species.class));
        }});
    }

}