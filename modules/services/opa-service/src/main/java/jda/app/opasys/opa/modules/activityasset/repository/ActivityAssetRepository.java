package jda.app.opasys.opa.modules.activityasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.activityasset.model.ActivityAsset;

@Repository
public interface ActivityAssetRepository extends PagingAndSortingRepository<ActivityAsset, Integer> {

}
