package br.com.guilinssolution.pettingCore.repositories;

import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsurRepository extends JpaRepository<UsurEntity, Integer>, QuerydslPredicateExecutor<UsurEntity> {

    Optional<UsurEntity> findByEmail(String email);
}
