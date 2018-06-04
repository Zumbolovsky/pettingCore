package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;

public interface PostAnimalRepositoryCustom {

    boolean existsByEntity(PostAnimalEntity entity);

}
