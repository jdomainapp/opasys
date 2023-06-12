package jda.app.opasys.project.modules.issue.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.issue.model.Issue;


@Repository
public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer>  {

}
