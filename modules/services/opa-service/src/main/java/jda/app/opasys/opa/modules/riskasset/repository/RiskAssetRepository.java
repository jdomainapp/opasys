package jda.app.opasys.opa.modules.riskasset.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.riskasset.model.RiskAsset;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface RiskAssetRepository extends PagingAndSortingRepository<RiskAsset, Integer>{
}
