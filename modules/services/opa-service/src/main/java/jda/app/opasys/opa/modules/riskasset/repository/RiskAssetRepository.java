package jda.app.opasys.opa.modules.riskasset.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.commonknowledgeasset.model.CommonKnowledgeAsset;
import jda.app.opasys.opa.modules.riskasset.model.RiskAsset;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface RiskAssetRepository extends PagingAndSortingRepository<RiskAsset, Integer>{
	List<RiskAsset> findByProjectId (int projectId);
	List<RiskAsset> findByActivityId (int activityId);
}
