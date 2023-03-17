package jda.app.opasys.knowledgeasset.modules.metricasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.knowledgeasset.modules.metricasset.model.MetricAsset;

@Repository
public interface MetricAssetRepository extends PagingAndSortingRepository<MetricAsset, Integer>{

}
