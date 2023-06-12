package jda.app.opasys.project.modules.risk.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.risk.model.Risk;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface RiskRepository extends PagingAndSortingRepository<Risk, Integer>{
}
