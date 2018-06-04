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
    public boolean existsByEntity(PostAnimalEntity entity) {
        JPAQuery<PostAnimalEntity> query = new JPAQuery<>(this.entityManager);
        QPostAnimalEntity postAnimalEntity = QPostAnimalEntity.postAnimalEntity;

        return query
                .from(postAnimalEntity)
                .where(postAnimalEntity.titlePostAnimal.eq(entity.getTitlePostAnimal())
                        .and(postAnimalEntity.descriptionPostAnimal.eq(entity.getDescriptionPostAnimal())
                        .and(postAnimalEntity.sizePostAnimal.eq(entity.getSizePostAnimal()))))
                .fetchOne() != null;
    }

}
