package jda.app.opasys.project.modules.issueasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.issueasset.model.IssueAsset;


@Repository
public interface IssueAssetRepository extends PagingAndSortingRepository<IssueAsset, Integer>  {

}
