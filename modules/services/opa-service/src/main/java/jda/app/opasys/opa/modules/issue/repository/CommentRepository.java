package jda.app.opasys.opa.modules.issue.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.issue.model.Comment;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>  {

}
