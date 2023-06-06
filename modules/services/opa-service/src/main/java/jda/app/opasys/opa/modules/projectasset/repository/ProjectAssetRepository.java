package jda.app.opasys.opa.modules.projectasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.projectasset.model.ProjectAsset;

@Repository
public interface ProjectAssetRepository extends PagingAndSortingRepository<ProjectAsset, Integer> {

}
