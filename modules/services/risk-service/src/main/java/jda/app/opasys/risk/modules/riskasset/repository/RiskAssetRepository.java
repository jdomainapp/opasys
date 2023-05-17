package jda.app.opasys.risk.modules.riskasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.risk.modules.riskasset.model.RiskAsset;


@Repository
public interface RiskAssetRepository extends PagingAndSortingRepository<RiskAsset, Integer>{

}
