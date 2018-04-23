package br.com.guilinssolution.pettingCore.repositories;

import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostAnimalRepository extends JpaRepository<PostAnimalEntity, Integer>, QuerydslPredicateExecutor<PostAnimalEntity> {

}
