package jda.app.opasys.issue.modules.commentasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.issue.modules.commentasset.model.CommentAsset;

@Repository
public interface CommentAssetRepository extends PagingAndSortingRepository<CommentAsset, Integer>  {

}
