package jda.app.opasys.opa.modules.metricasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.app.opasys.opa.modules.metricasset.model.MetricAsset;
import jda.app.opasys.opa.modules.planasset.model.PlanAsset;

@Repository
public interface MetricAssetRepository extends PagingAndSortingRepository<MetricAsset, Integer>{
	List<MetricAsset> findByProjectId (int projectId);
	List<MetricAsset> findByActivityId (int activityId);
}
