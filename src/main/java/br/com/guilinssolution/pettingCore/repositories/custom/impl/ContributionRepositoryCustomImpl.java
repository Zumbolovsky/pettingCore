package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.ContributionAdapter;
import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.enums.Kind;
import br.com.guilinssolution.pettingCore.model.example.ListResultExample;
import br.com.guilinssolution.pettingCore.model.example.PageExample;
import br.com.guilinssolution.pettingCore.model.entities.*;
import br.com.guilinssolution.pettingCore.repositories.custom.ContributionRepositoryCustom;
import com.querydsl.core.QueryModifiers;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class ContributionRepositoryCustomImpl implements ContributionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListResultExample<ContributionDTO> listByDonator(Integer idUsur, PageExample pageExample, Kind kind) {
        QContributionEntity contribution = QContributionEntity.contributionEntity;
        JPAQuery<ContributionEntity> query = new JPAQuery<>(this.entityManager);

        Pageable pageable = PageHelper.getPage(pageExample);

        long limit = Integer.toUnsignedLong(pageable.getPageSize());
        long offset = pageable.getOffset();

        QueryModifiers modifiers = new QueryModifiers(limit, offset);

        switch (kind) {
            case ANIMAL:
                query = query
                        .select(contribution)
                        .from(contribution)
                        .where(contribution.usurEntityByIdRequest.idUsur.eq(idUsur)
                                .and(contribution.postItemEntity.isNull()))
                        .restrict(modifiers);
            case ITEM:
                query = query
                        .select(contribution)
                        .from(contribution)
                        .where(contribution.usurEntityByIdRequest.idUsur.eq(idUsur)
                                .and(contribution.postAnimalEntity.isNull()))
                        .restrict(modifiers);
            default:
                query = query
                        .select(contribution)
                        .from(contribution)
                        .where(contribution.usurEntityByIdRequest.idUsur.eq(idUsur))
                        .restrict(modifiers);
        }

        List<ContributionEntity> entityList = query.fetch();
        List<ContributionDTO> dtoList = entityList.stream().map(ContributionAdapter::convertToDTO).collect(Collectors.toList());
        Page<ContributionDTO> page = new PageImpl<>(dtoList, pageable, query.fetchCount());

        return new ListResultExample<>(page, dtoList);
    }

}
