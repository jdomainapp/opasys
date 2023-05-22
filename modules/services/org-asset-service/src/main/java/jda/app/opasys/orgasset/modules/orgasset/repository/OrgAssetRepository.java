package jda.app.opasys.orgasset.modules.orgasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.orgasset.modules.orgasset.model.OrgAsset;

@Repository
public interface OrgAssetRepository extends PagingAndSortingRepository<OrgAsset, Integer>{

}
