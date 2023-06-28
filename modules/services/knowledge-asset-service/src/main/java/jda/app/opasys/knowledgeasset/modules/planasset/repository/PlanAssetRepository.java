package jda.app.opasys.knowledgeasset.modules.planasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.knowledgeasset.modules.metricasset.model.MetricAsset;
import jda.app.opasys.knowledgeasset.modules.planasset.model.PlanAsset;

@Repository
public interface PlanAssetRepository extends PagingAndSortingRepository<PlanAsset, Integer>{
	List<PlanAsset> findByProjectId (int projectId);
	List<PlanAsset> findByActivityId (int activityId);
}
