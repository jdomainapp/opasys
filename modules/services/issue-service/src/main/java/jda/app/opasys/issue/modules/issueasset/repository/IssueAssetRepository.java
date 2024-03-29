package jda.app.opasys.issue.modules.issueasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.issue.modules.issueasset.model.IssueAsset;

@Repository
public interface IssueAssetRepository extends PagingAndSortingRepository<IssueAsset, Integer>  {
	List<IssueAsset> findByProjectId (int projectId);
	List<IssueAsset> findByActivityId (int activityId);
}
