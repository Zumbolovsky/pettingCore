package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.QAnimalEntity;
import br.com.guilinssolution.pettingCore.repositories.custom.AnimalRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AnimalRepositoryCustomImpl implements AnimalRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsByEntitySave(AnimalEntity entity) {
        JPAQuery<AnimalEntity> query = new JPAQuery<>(this.entityManager);
        QAnimalEntity animalEntity = QAnimalEntity.animalEntity;

        return query
                .from(animalEntity)
                .where(animalEntity.breedAnimal.eq(entity.getBreedAnimal())
                        .and(animalEntity.speciesAnimal.eq(entity.getSpeciesAnimal())))
                .fetchOne() != null;
    }

    @Override
    public boolean existsByEntityUpdate(AnimalEntity entity) {
        JPAQuery<AnimalEntity> query = new JPAQuery<>(this.entityManager);
        QAnimalEntity animalEntity = QAnimalEntity.animalEntity;

        return query
                .from(animalEntity)
                .where(animalEntity.breedAnimal.eq(entity.getBreedAnimal())
                        .and(animalEntity.speciesAnimal.eq(entity.getSpeciesAnimal())))
                .fetch().size() > 1;
    }
}
