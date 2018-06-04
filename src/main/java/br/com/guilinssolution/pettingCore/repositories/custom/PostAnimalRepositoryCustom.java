package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;

public interface PostAnimalRepositoryCustom {

    boolean existsByEntitySave(PostAnimalEntity entity);
    boolean existsByEntityUpdate(PostAnimalEntity entity);

}
