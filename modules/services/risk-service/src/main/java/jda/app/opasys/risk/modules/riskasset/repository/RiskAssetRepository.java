package jda.app.opasys.risk.modules.riskasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.risk.modules.riskasset.model.RiskAsset;


@Repository
public interface RiskAssetRepository extends PagingAndSortingRepository<RiskAsset, Integer>{
	List<RiskAsset> findByProjectId (int projectId);
	List<RiskAsset> findByActivityId (int activityId);
}
