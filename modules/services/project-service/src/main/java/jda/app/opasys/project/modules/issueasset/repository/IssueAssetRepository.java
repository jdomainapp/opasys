package jda.app.opasys.project.modules.issueasset.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.issueasset.model.IssueAsset;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IssueAssetRepository extends PagingAndSortingRepository<IssueAsset, Integer>{
}
