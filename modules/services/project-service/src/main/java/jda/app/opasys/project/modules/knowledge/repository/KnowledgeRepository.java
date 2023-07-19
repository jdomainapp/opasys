package jda.app.opasys.project.modules.knowledge.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.knowledge.model.Knowledge;


@Repository
public interface KnowledgeRepository extends PagingAndSortingRepository<Knowledge, Integer>  {
	List<Knowledge> findByProjectId (int projectId);
	List<Knowledge> findByType (String type);
}
