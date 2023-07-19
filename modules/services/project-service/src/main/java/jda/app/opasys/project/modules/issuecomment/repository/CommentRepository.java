package jda.app.opasys.project.modules.issuecomment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.issuecomment.model.Comment;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>  {

}
