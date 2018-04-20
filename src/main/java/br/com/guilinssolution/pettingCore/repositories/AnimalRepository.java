package br.com.guilinssolution.pettingCore.repositories;

import br.com.guilinssolution.pettingCore.model.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer>, QuerydslPredicateExecutor<AnimalEntity> {

}
