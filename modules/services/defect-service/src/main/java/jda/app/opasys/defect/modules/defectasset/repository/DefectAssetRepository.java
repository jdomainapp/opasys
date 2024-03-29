package jda.app.opasys.defect.modules.defectasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.defect.modules.defectasset.model.DefectAsset;

@Repository
public interface DefectAssetRepository extends PagingAndSortingRepository<DefectAsset, Integer>  {
	List<DefectAsset> findByProjectId (int projectId);
	List<DefectAsset> findByActivityId (int activityId);
}
