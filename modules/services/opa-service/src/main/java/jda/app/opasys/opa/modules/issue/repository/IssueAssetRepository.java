package jda.app.opasys.opa.modules.issue.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.issue.model.IssueAsset;


@Repository
public interface IssueAssetRepository extends PagingAndSortingRepository<IssueAsset, Integer>  {

}
