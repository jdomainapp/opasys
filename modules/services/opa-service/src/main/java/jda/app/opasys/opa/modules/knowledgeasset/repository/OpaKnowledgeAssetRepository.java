package jda.app.opasys.opa.modules.knowledgeasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.app.opasys.opa.modules.knowledgeasset.model.OpaKnowledgeAsset;

@Repository
public interface OpaKnowledgeAssetRepository extends PagingAndSortingRepository<OpaKnowledgeAsset, Integer>{
	List<OpaKnowledgeAsset> findByProjectId (int projectId);
	List<OpaKnowledgeAsset> findByActivityId (int activityId);
}
