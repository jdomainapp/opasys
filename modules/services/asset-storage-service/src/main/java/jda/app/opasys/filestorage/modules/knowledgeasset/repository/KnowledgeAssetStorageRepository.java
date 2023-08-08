package jda.app.opasys.filestorage.modules.knowledgeasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.filestorage.modules.knowledgeasset.model.KnowledgeAssetStorage;

@Repository
public interface KnowledgeAssetStorageRepository extends PagingAndSortingRepository<KnowledgeAssetStorage, Integer>{

}
