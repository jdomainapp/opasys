package jda.app.opasys.project.modules.project.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.project.model.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer> {

}
