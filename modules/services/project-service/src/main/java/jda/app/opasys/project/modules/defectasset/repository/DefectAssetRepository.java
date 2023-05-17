package jda.app.opasys.project.modules.defectasset.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.defectasset.model.DefectAsset;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface DefectAssetRepository extends PagingAndSortingRepository<DefectAsset, Integer>{
}
