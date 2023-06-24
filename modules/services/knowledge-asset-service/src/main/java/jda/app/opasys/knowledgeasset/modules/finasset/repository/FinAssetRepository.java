package jda.app.opasys.knowledgeasset.modules.finasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.knowledgeasset.modules.finasset.model.FinAsset;

@Repository
public interface FinAssetRepository extends PagingAndSortingRepository<FinAsset, Integer>{
	List<FinAsset> findByProjectId (int projectId);
	List<FinAsset> findByActivityId (int activityId);
}
