package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.dto.PostItemDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import br.com.guilinssolution.pettingCore.model.entities.QPostItemEntity;
import br.com.guilinssolution.pettingCore.repositories.custom.PostItemRepositoryCustom;
import com.querydsl.core.QueryModifiers;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PostItemRepositoryCustomImpl implements PostItemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListResultDTO<PostItemDTO> listByUsur(Integer idUsur, PageDTO pageDTO) {
        QPostItemEntity postItem = QPostItemEntity.postItemEntity;
        JPAQuery<PostItemDTO> query = new JPAQuery<>(this.entityManager);

        Pageable pageable = PageHelper.getPage(pageDTO);
        QueryModifiers modifiers = new QueryModifiers(Integer.toUnsignedLong(pageable.getPageSize()), pageable.getOffset());

        query = query//todo: resolver construtor
                .select(Projections.constructor(PostItemDTO.class,
                        postItem.idPostItem, postItem.titlePostItem,
                        postItem.descriptionPostItem, postItem.imagePostItem,
                        postItem.typePostItem))
                .from(postItem)
                .where(postItem.usurEntity.idUsur.eq(idUsur))
                .restrict(modifiers);

        List<PostItemDTO> list = query.fetch();

        return new ListResultDTO<>(pageable, list, query);
    }

}
