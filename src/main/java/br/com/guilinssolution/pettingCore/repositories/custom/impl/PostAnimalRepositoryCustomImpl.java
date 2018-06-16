package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostAnimalEntity;
import br.com.guilinssolution.pettingCore.repositories.custom.PostAnimalRepositoryCustom;
import com.querydsl.core.QueryModifiers;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class PostAnimalRepositoryCustomImpl implements PostAnimalRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListResultExample<PostAnimalDTO> listByUsur(Integer idUsur, PageExample pageExample) {
        QPostAnimalEntity postAnimal = QPostAnimalEntity.postAnimalEntity;
        JPAQuery<PostAnimalEntity> query = new JPAQuery<>(this.entityManager);

        Pageable pageable = PageHelper.getPage(pageExample);

        long limit = Integer.toUnsignedLong(pageable.getPageSize());
        long offset = pageable.getOffset();

        QueryModifiers modifiers = new QueryModifiers(limit, offset);

        query = query
                .select(postAnimal)
                .from(postAnimal)
                .where(postAnimal.usurEntity.idUsur.eq(idUsur))
                .restrict(modifiers);

        List<PostAnimalEntity> entityList = query.fetch();
        List<PostAnimalDTO> dtoList = entityList.stream().map(PostAnimalAdapter::convertToDTO).collect(Collectors.toList());
        Page<PostAnimalDTO> page = new PageImpl<>(dtoList, pageable, query.fetchCount());

        return new ListResultExample<>(page, dtoList);
    }

}
