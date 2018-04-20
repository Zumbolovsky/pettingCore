package br.com.guilinssolution.pettingCore.repositories;

import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostItemRepository extends JpaRepository<PostItemEntity, Integer>, QuerydslPredicateExecutor<PostItemEntity> {

}
