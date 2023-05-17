package jda.app.opasys.opa.modules.defectasset.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.defectasset.model.DefectAsset;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface DefectAssetRepository extends PagingAndSortingRepository<DefectAsset, Integer>{
}
