package br.com.guilinssolution.pettingCore.repositories;

import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostAnimalRepository extends JpaRepository<PostAnimalEntity, Integer>, QuerydslPredicateExecutor<PostAnimalEntity> {

}
