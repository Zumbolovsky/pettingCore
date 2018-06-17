package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;

public interface PostAnimalRepositoryCustom {

    ListResultExample<PostAnimalDTO> listByUsur(Integer idUsur, PageExample pageExample);
    ListResultExample<PostAnimalDTO> findAllCustom(PostAnimalDTO dto, Pageable pageable);
}
