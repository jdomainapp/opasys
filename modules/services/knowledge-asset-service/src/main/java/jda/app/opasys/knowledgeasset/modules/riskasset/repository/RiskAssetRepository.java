package jda.app.opasys.knowledgeasset.modules.riskasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.knowledgeasset.modules.riskasset.model.RiskAsset;

@Repository
public interface RiskAssetRepository extends PagingAndSortingRepository<RiskAsset, Integer>{

}
