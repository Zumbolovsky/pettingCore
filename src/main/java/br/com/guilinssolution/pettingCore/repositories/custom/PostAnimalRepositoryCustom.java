package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostAnimalRepositoryCustom {

    ListResultExample<PostAnimalDTO> listByUsur(Integer idUsur, PageExample pageExample);
    ListResultExample<PostAnimalDTO> findAllCustom(PostAnimalDTO dto, Pageable pageable);
    List<Integer> customCount();
}
