package jda.app.opasys.opa.modules.configasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.configasset.model.ConfigAsset;
import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.app.opasys.opa.modules.planasset.model.PlanAsset;

@Repository
public interface ConfigAssetRepository extends PagingAndSortingRepository<ConfigAsset, Integer>{
	List<ConfigAsset> findByProjectId (int projectId);
	List<ConfigAsset> findByActivityId (int activityId);
}
