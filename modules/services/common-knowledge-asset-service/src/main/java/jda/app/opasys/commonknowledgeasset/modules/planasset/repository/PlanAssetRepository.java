package jda.app.opasys.commonknowledgeasset.modules.planasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.commonknowledgeasset.modules.metricasset.model.MetricAsset;
import jda.app.opasys.commonknowledgeasset.modules.planasset.model.PlanAsset;

@Repository
public interface PlanAssetRepository extends PagingAndSortingRepository<PlanAsset, Integer>{
	List<PlanAsset> findByProjectId (int projectId);
	List<PlanAsset> findByActivityId (int activityId);
}
