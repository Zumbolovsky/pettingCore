package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.helper.PageHelper;
import br.com.guilinssolution.pettingCore.model.adapter.ContributionAdapter;
import br.com.guilinssolution.pettingCore.model.dto.ContributionDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.ListResultDTO;
import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
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
    public ListResultDTO<ContributionDTO> listByDonator(Integer idUsur, PageDTO pageDTO) {
        QContributionEntity contribution = QContributionEntity.contributionEntity;
        JPAQuery<ContributionEntity> query = new JPAQuery<>(this.entityManager);

        Pageable pageable = PageHelper.getPage(pageDTO);

        long limit = Integer.toUnsignedLong(pageable.getPageSize());
        long offset = pageable.getOffset();

        QueryModifiers modifiers = new QueryModifiers(limit, offset);

        query = query
                .select(contribution)
                .from(contribution)
                .where(contribution.usurEntityByIdDonator.idUsur.eq(idUsur))
                .restrict(modifiers);

        List<ContributionEntity> entityList = query.fetch();
        List<ContributionDTO> dtoList = entityList.stream().map(ContributionAdapter::convertToDTO).collect(Collectors.toList());
        Page<ContributionDTO> page = new PageImpl<>(dtoList, pageable, query.fetchCount());

        return new ListResultDTO<>(page, dtoList);
    }

    @Override
    public boolean existsByEntity(ContributionEntity entity) {
        JPAQuery<ContributionEntity> query = new JPAQuery<>(this.entityManager);
        QContributionEntity contributionEntity = QContributionEntity.contributionEntity;

        return query
                .from(contributionEntity)
                .where(contributionEntity.descriptionContribution.eq(entity.getDescriptionContribution()))
                .fetchOne() != null;
    }

}
