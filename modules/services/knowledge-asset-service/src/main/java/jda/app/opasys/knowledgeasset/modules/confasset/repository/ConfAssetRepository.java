package jda.app.opasys.knowledgeasset.modules.confasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.knowledgeasset.modules.confasset.model.ConfAsset;

@Repository
public interface ConfAssetRepository extends PagingAndSortingRepository<ConfAsset, Integer>{
	List<ConfAsset> findByProjectId (int projectId);
	List<ConfAsset> findByActivityId (int activityId);
}
