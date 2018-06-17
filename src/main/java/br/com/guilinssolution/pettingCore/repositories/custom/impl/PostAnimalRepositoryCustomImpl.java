package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostAnimalAdapter;
import br.com.guilinssolution.pettingCore.model.dto.PostAnimalDTO;
import br.com.guilinssolution.pettingCore.model.entities.QContributionEntity;
import br.com.guilinssolution.pettingCore.model.enums.ConvertType;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostAnimalEntity;
import br.com.guilinssolution.pettingCore.repositories.custom.PostAnimalRepositoryCustom;
import com.querydsl.core.QueryModifiers;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public ListResultExample<PostAnimalDTO> findAllCustom(PostAnimalDTO dto, Pageable pageable) {
        QPostAnimalEntity postAnimal = QPostAnimalEntity.postAnimalEntity;
        QContributionEntity contributionEntity = QContributionEntity.contributionEntity;
        JPAQuery<PostAnimalEntity> query = new JPAQuery<>(this.entityManager);
        JPAQuery<Integer> subQuery = new JPAQuery<>(this.entityManager);

        long limit = Integer.toUnsignedLong(pageable.getPageSize());
        long offset = pageable.getOffset();

        QueryModifiers modifiers = new QueryModifiers(limit, offset);

        BooleanExpression filter = null;
        if (StringUtils.isNotEmpty(dto.getDescriptionPostAnimal())) {
            filter = postAnimal.descriptionPostAnimal.like("%"+dto.getDescriptionPostAnimal()+"%");
        }
        if (dto.getSizePostAnimal() != null) {
            filter = filter != null ?
                    filter.and(postAnimal.sizePostAnimal.eq(dto.getSizePostAnimal())) :
                    postAnimal.sizePostAnimal.eq(dto.getSizePostAnimal());
        }
        if (StringUtils.isNotEmpty(dto.getTitlePostAnimal())) {
            filter = filter != null ?
                    filter.and(postAnimal.titlePostAnimal.like("%"+dto.getTitlePostAnimal()+"%")) :
                    postAnimal.titlePostAnimal.like("%"+dto.getTitlePostAnimal()+"%");
        }
        if (dto.getSpeciesPostAnimal() != null) {
            filter = filter != null ?
                    filter.and(postAnimal.speciesPostAnimal.eq(dto.getSpeciesPostAnimal())) :
                    postAnimal.speciesPostAnimal.eq(dto.getSpeciesPostAnimal());
        }

        subQuery
                .select(contributionEntity.postAnimalEntity.idPostAnimal)
                .from(contributionEntity);
        BooleanExpression condition = postAnimal.idPostAnimal.notIn(subQuery.fetch());
        query
                .select(postAnimal)
                .from(postAnimal)
                .join(contributionEntity)
                .on(contributionEntity.postAnimalEntity.idPostAnimal.eq(postAnimal.idPostAnimal))
                .where(filter != null ? filter.and(condition) : condition)
                .restrict(modifiers);

        List<PostAnimalEntity> entityList = query.fetch();
        List<PostAnimalDTO> dtoList = entityList.stream().map(PostAnimalAdapter::convertToDTO).collect(Collectors.toList());
        Page<PostAnimalDTO> page = new PageImpl<>(dtoList, pageable, query.fetchCount());

        return new ListResultExample<>(page, dtoList);
    }

}
