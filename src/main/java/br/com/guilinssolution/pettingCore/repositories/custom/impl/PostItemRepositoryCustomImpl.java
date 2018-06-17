package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.PostItemAdapter;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.entities.QContributionEntity;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostItemEntity;
import br.com.guilinssolution.pettingCore.repositories.custom.PostItemRepositoryCustom;
import com.querydsl.core.QueryModifiers;
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

public class PostItemRepositoryCustomImpl implements PostItemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListResultExample<PostItemDTO> listByUsur(Integer idUsur, PageExample pageExample) {
        QPostItemEntity postItem = QPostItemEntity.postItemEntity;
        JPAQuery<PostItemEntity> query = new JPAQuery<>(this.entityManager);

        Pageable pageable = PageHelper.getPage(pageExample);

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

        return new ListResultExample<>(page, dtoList);
    }

    @Override
    public ListResultExample<PostItemDTO> findAllCustom(PostItemDTO dto, Pageable pageable) {
        QPostItemEntity postItem = QPostItemEntity.postItemEntity;
        QContributionEntity contributionEntity = QContributionEntity.contributionEntity;
        JPAQuery<PostItemEntity> query = new JPAQuery<>(this.entityManager);

        long limit = Integer.toUnsignedLong(pageable.getPageSize());
        long offset = pageable.getOffset();

        QueryModifiers modifiers = new QueryModifiers(limit, offset);

        BooleanExpression expression = null;
        if (StringUtils.isNotEmpty(dto.getDescriptionPostItem())) {
            expression = postItem.descriptionPostItem.like("%"+dto.getDescriptionPostItem()+"%");
        }
        if (StringUtils.isNotEmpty(dto.getTitlePostItem())) {
            expression = expression != null ?
                    expression.and(postItem.titlePostItem.like("%"+dto.getTitlePostItem()+"%")) :
                    postItem.titlePostItem.like("%"+dto.getTitlePostItem()+"%");
        }
        if (dto.getSpeciesPostItem() != null) {
            expression = expression != null ?
                    expression.and(postItem.speciesPostItem.eq(dto.getSpeciesPostItem())) :
                    postItem.speciesPostItem.eq(dto.getSpeciesPostItem());
        }
        if (dto.getTypePostItem() != null) {
            expression = expression != null ?
                    expression.and(postItem.typePostItem.eq(dto.getTypePostItem())) :
                    postItem.typePostItem.eq(dto.getTypePostItem());
        }

        query
                .select(postItem)
                .from(postItem)
                .join(contributionEntity)
                .on(contributionEntity.postItemEntity.idPostItem.eq(postItem.idPostItem))
                .where(expression != null ? expression.and(contributionEntity.usurEntityByIdDonator.isNull()) :
                        contributionEntity.usurEntityByIdDonator.isNull())
                .restrict(modifiers);

        List<PostItemEntity> entityList = query.fetch();
        List<PostItemDTO> dtoList = entityList.stream().map(PostItemAdapter::convertToDTO).collect(Collectors.toList());
        Page<PostItemDTO> page = new PageImpl<>(dtoList, pageable, query.fetchCount());

        return new ListResultExample<>(page, dtoList);
    }

}
