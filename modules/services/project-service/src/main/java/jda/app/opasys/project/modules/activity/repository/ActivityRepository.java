package jda.app.opasys.project.modules.activity.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.activity.model.Activity;

@Repository
public interface ActivityRepository extends PagingAndSortingRepository<Activity, Integer> {
	List<Activity> findByProjectId (int projectId);
}
