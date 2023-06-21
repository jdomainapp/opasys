package jda.app.opasys.project.modules.knowlegde.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.knowlegde.model.Knowledge;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface KnowledgeRepository extends PagingAndSortingRepository<Knowledge, Integer>{
	Optional<Knowledge> findByProjectId(int projectID);
}
