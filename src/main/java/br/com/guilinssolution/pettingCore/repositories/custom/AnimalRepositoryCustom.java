package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;

public interface AnimalRepositoryCustom {

    boolean existsByEntitySave(AnimalEntity entity);
    boolean existsByEntityUpdate(AnimalEntity entity);
}
