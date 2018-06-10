package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostItemAdapter;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostItemEntity;
import br.com.guilinssolution.pettingCore.repositories.custom.PostItemRepositoryCustom;
import com.querydsl.core.QueryModifiers;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class PostItemRepositoryCustomImpl implements PostItemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListResultDTO<PostItemDTO> listByUsur(Integer idUsur, PageDTO pageDTO) {
        QPostItemEntity postItem = QPostItemEntity.postItemEntity;
        JPAQuery<PostItemEntity> query = new JPAQuery<>(this.entityManager);

        Pageable pageable = PageHelper.getPage(pageDTO);

        long limit = Integer.toUnsignedLong(pageable.getPageSize());
        long offset = pageable.getOffset();

        QueryModifiers modifiers = new QueryModifiers(limit, offset);

        query = query
                .select(postItem)
                .from(postItem)
                .where(postItem.usurEntity.idUsur.eq(idUsur))
                .restrict(modifiers);

        List<PostItemEntity> entityList = query.fetch();
        List<PostItemDTO> dtoList = entityList.stream().map(PostItemAdapter::convertToDTO).collect(Collectors.toList());
        Page<PostItemDTO> page = new PageImpl<>(dtoList, pageable, query.fetchCount());

        return new ListResultDTO<>(page, dtoList);
    }

    @Override
    public boolean existsByEntity(PostItemEntity entity) {
        JPAQuery<PostItemEntity> query = new JPAQuery<>(this.entityManager);
        QPostItemEntity postItemEntity = QPostItemEntity.postItemEntity;

        return query
                .from(postItemEntity)
                .where(postItemEntity.titlePostItem.eq(entity.getTitlePostItem())
                        .and(postItemEntity.descriptionPostItem.eq(entity.getDescriptionPostItem()))
                        .and(postItemEntity.speciesPostItem.eq(entity.getSpeciesPostItem())))
                .fetchOne() != null;
    }

}
