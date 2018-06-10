package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
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
    public ListResultDTO<PostAnimalDTO> listByUsur(Integer idUsur, PageDTO pageDTO) {
        QPostAnimalEntity postAnimal = QPostAnimalEntity.postAnimalEntity;
        JPAQuery<PostAnimalEntity> query = new JPAQuery<>(this.entityManager);

        Pageable pageable = PageHelper.getPage(pageDTO);

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

        return new ListResultDTO<>(page, dtoList);
    }

    @Override
    public boolean existsByEntity(PostAnimalEntity entity) {
        JPAQuery<PostAnimalEntity> query = new JPAQuery<>(this.entityManager);
        QPostAnimalEntity postAnimalEntity = QPostAnimalEntity.postAnimalEntity;

        return query
                .from(postAnimalEntity)
                .where(postAnimalEntity.titlePostAnimal.eq(entity.getTitlePostAnimal())
                        .and(postAnimalEntity.descriptionPostAnimal.eq(entity.getDescriptionPostAnimal())
                        .and(postAnimalEntity.sizePostAnimal.eq(entity.getSizePostAnimal()))
                        .and(postAnimalEntity.speciesPostAnimal.eq(entity.getSpeciesPostAnimal()))))
                .fetchOne() != null;
    }

}
