package jda.app.opasys.config.modules.configasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.config.modules.configasset.model.ConfigAsset;

@Repository
public interface ConfigAssetRepository extends PagingAndSortingRepository<ConfigAsset, Integer>{
	List<ConfigAsset> findByProjectId (int projectId);
	List<ConfigAsset> findByActivityId (int activityId);
}
