package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;

public interface UsurRepositoryCustom {

    UsurEntity findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEntity(UsurEntity usurEntity);

}
