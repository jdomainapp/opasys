package jda.app.opasys.opa.modules.commonknowledgeasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.commonknowledgeasset.model.CommonKnowledgeAsset;
import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;

@Repository
public interface CommonKnowledgeAssetRepository extends PagingAndSortingRepository<CommonKnowledgeAsset, Integer>{
	List<CommonKnowledgeAsset> findByProjectId (int projectId);
	List<CommonKnowledgeAsset> findByActivityId (int activityId);
}
