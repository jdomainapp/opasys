package jda.app.opasys.commonknowledgeasset.modules.metricasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.commonknowledgeasset.modules.finasset.model.FinAsset;
import jda.app.opasys.commonknowledgeasset.modules.metricasset.model.MetricAsset;

@Repository
public interface MetricAssetRepository extends PagingAndSortingRepository<MetricAsset, Integer>{
	List<MetricAsset> findByProjectId (int projectId);
	List<MetricAsset> findByActivityId (int activityId);
}
