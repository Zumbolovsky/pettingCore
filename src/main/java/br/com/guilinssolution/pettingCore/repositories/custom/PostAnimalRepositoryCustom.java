package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;

public interface PostAnimalRepositoryCustom {

    ListResultExample<PostAnimalDTO> listByUsur(Integer idUsur, PageExample pageExample);

}
