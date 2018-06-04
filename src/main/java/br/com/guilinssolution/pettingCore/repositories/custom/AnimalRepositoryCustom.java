package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;

public interface AnimalRepositoryCustom {

    boolean existsByEntity(AnimalEntity entity);

}
