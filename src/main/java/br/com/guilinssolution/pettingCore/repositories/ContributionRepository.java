package br.com.guilinssolution.pettingCore.repositories;

import br.com.guilinssolution.pettingCore.model.entities.ContributionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<ContributionEntity, Integer>, QuerydslPredicateExecutor<ContributionEntity> {
    
}
