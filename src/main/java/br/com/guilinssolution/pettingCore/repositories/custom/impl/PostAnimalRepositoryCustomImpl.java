package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QPostAnimalEntity;
import br.com.guilinssolution.pettingCore.repositories.custom.PostAnimalRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PostAnimalRepositoryCustomImpl implements PostAnimalRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsByEntitySave(PostAnimalEntity entity) {
        JPAQuery<PostAnimalEntity> query = new JPAQuery<>(this.entityManager);
        QPostAnimalEntity postAnimalEntity = QPostAnimalEntity.contributionEntity;

        return query
                .from(contributionEntity)
                .where(contributionEntity.descriptionContribution.eq(entity.getDescriptionContribution()))
                .fetchOne() != null;

    }

    @Override
    public boolean existsByEntityUpdate(PostAnimalEntity entity) {
        return false;
    }
}
