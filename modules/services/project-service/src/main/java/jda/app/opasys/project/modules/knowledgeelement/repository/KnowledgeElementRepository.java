package jda.app.opasys.project.modules.knowledgeelement.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.knowledgeelement.model.KnowledgeElement;


@Repository
public interface KnowledgeElementRepository extends PagingAndSortingRepository<KnowledgeElement, Integer>  {
	List<KnowledgeElement> findByProjectId (int projectId);
	List<KnowledgeElement> findByType (String type);
}
