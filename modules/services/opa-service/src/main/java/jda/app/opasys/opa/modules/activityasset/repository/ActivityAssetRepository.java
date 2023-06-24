package jda.app.opasys.opa.modules.activityasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.activityasset.model.ActivityAsset;
import jda.app.opasys.opa.modules.riskasset.model.RiskAsset;

@Repository
public interface ActivityAssetRepository extends PagingAndSortingRepository<ActivityAsset, Integer> {
	List<ActivityAsset> findByProjectId (int projectId);
}
