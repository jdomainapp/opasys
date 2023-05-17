package jda.app.opasys.opa.modules.issueasset.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IssueAssetRepository extends PagingAndSortingRepository<IssueAsset, Integer>{
}
