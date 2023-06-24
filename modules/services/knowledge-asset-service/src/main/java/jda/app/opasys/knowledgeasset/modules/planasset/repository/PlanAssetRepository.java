package jda.app.opasys.knowledgeasset.modules.planasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.knowledgeasset.modules.metricasset.model.MetricAsset;
import jda.app.opasys.knowledgeasset.modules.planasset.model.PlanAsset;

@Repository
public interface PlanAssetRepository extends PagingAndSortingRepository<PlanAsset, Integer>{
	List<MetricAsset> findByProjectId (int projectId);
	List<MetricAsset> findByActivityId (int activityId);
}
