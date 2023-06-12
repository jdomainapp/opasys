package jda.app.opasys.opa.modules.issueasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.issueasset.model.CommentAsset;

@Repository
public interface CommentAssetRepository extends PagingAndSortingRepository<CommentAsset, Integer>  {

}
