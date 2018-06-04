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

    @Override
    public boolean existsByEmailSave(String email) {
        JPAQuery<UsurEntity> query = new JPAQuery<>(this.entityManager);
        QUsurEntity usurEntity = QUsurEntity.usurEntity;

        return query
                .from(usurEntity)
                .where(usurEntity.emailUsur.eq(email))
                .fetchOne() != null;
    }

    @Override
    public boolean existsByEmailUpdate(String email) {
        JPAQuery<UsurEntity> query = new JPAQuery<>(this.entityManager);
        QUsurEntity usurEntity = QUsurEntity.usurEntity;

        return query
                .from(usurEntity)
                .where(usurEntity.emailUsur.eq(email))
                .fetch().size() > 1;
    }

    @Override
    public boolean existsByEntitySave(UsurEntity entity) {
        JPAQuery<UsurEntity> query = new JPAQuery<>(this.entityManager);
        QUsurEntity usurEntity = QUsurEntity.usurEntity;

        return query
                .from(usurEntity)
                .where(usurEntity.nameUsur.eq(entity.getNameUsur())
                        .and(usurEntity.emailUsur.eq(entity.getEmailUsur())
                        .and(usurEntity.passwordUsur.eq(entity.getPasswordUsur())
                        .and(usurEntity.addressUsur.eq(entity.getAddressUsur())
                        .and(usurEntity.cityUsur.eq(entity.getCityUsur())
                        .and(usurEntity.cpfUsur.eq(entity.getCpfUsur())
                        .and(usurEntity.cellphoneUsur.eq(entity.getCellphoneUsur())
                        .and(usurEntity.phoneUsur.eq(entity.getPhoneUsur())
                        .and(usurEntity.stateUsur.eq(entity.getStateUsur()))))))))))
                .fetchOne() != null;
    }

    @Override
    public boolean existsByEntityUpdate(UsurEntity entity) {
        JPAQuery<UsurEntity> query = new JPAQuery<>(this.entityManager);
        QUsurEntity usurEntity = QUsurEntity.usurEntity;

        return query
                .from(usurEntity)
                .where(usurEntity.nameUsur.eq(entity.getNameUsur())
                        .and(usurEntity.emailUsur.eq(entity.getEmailUsur())
                        .and(usurEntity.passwordUsur.eq(entity.getPasswordUsur())
                        .and(usurEntity.addressUsur.eq(entity.getAddressUsur())
                        .and(usurEntity.cityUsur.eq(entity.getCityUsur())
                        .and(usurEntity.cpfUsur.eq(entity.getCpfUsur())
                        .and(usurEntity.cellphoneUsur.eq(entity.getCellphoneUsur())
                        .and(usurEntity.phoneUsur.eq(entity.getPhoneUsur())
                        .and(usurEntity.stateUsur.eq(entity.getStateUsur()))))))))))
                .fetch().size() > 1;
    }
}
