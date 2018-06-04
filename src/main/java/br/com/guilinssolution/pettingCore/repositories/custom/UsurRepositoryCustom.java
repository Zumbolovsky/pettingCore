package br.com.guilinssolution.pettingCore.repositories.custom;

import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;

public interface UsurRepositoryCustom {

    UsurEntity findByEmail(String email);
    boolean existsByEmailSave(String email);
    boolean existsByEmailUpdate(String email);
    boolean existsByEntitySave(UsurEntity usurEntity);
    boolean existsByEntityUpdate(UsurEntity usurEntity);
}
