package jda.app.opasys.project.modules.riskasset.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.riskasset.model.RiskAsset;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface RiskAssetRepository extends PagingAndSortingRepository<RiskAsset, Integer>{
}
