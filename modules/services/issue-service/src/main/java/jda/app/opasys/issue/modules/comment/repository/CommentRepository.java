package jda.app.opasys.issue.modules.comment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.issue.modules.comment.model.Comment;
import jda.app.opasys.issue.modules.issueasset.model.IssueAsset;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>  {

}
