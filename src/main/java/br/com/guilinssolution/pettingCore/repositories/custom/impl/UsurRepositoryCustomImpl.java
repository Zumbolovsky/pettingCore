package br.com.guilinssolution.pettingCore.repositories.custom.impl;

import br.com.guilinssolution.pettingCore.model.entities.QUsurEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.repositories.custom.UsurRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsurRepositoryCustomImpl implements UsurRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UsurEntity findByEmail(String email) {
        JPAQuery<UsurEntity> query = new JPAQuery<>(this.entityManager);
        QUsurEntity usurEntity = QUsurEntity.usurEntity;

        return query
                .select(usurEntity)
                .from(usurEntity)
                .where(usurEntity.emailUsur.eq(email))
                .fetchOne();
    }

}
