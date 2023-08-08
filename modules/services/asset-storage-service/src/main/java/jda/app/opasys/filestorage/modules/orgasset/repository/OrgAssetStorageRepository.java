package jda.app.opasys.filestorage.modules.orgasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.filestorage.modules.orgasset.model.OrgAssetStorage;

@Repository
public interface OrgAssetStorageRepository extends PagingAndSortingRepository<OrgAssetStorage, Integer>{

}
