package jda.app.opasys.knowledgeasset.modules.knowledgeasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.knowledgeasset.modules.knowledgeasset.model.KnowledgeAsset;

@Repository
public interface KnowledgeAssetRepository extends PagingAndSortingRepository<KnowledgeAsset, Integer>{

}
