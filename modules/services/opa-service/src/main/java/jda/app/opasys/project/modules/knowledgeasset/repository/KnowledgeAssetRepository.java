package jda.app.opasys.project.modules.knowledgeasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KnowledgeAssetRepository extends PagingAndSortingRepository<KnowledgeAssetRepository, Integer>{

}
