package br.com.guilinssolution.pettingCore.template.dto;

import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.enums.Size;
import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.template.CommonTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class PostAnimalDTOTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(PostAnimalDTO.class).addTemplate(RULE_VALID, new Rule() {{
            add("idPostAnimal", uniqueRandom(1, 2, 3));
            add("titlePostAnimal", random("Lorem", "ispum", "dolor"));
            add("descriptionPostAnimal", random("Lorem", "ispum", "dolor"));
            add("sizePostAnimal", random(Size.class));
            add("speciesPostAnimal", random(Species.class));
        }});
    }

}
