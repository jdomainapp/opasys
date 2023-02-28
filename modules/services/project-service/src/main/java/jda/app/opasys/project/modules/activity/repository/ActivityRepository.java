package jda.app.opasys.project.modules.activity.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import jda.app.opasys.project.modules.activity.model.Activity;

@Repository
public interface ActivityRepository extends PagingAndSortingRepository<Activity, Integer>{
	Optional<Activity> findByProjectId(int projectID);
}
