package jda.app.opasys.opa.modules.financeasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.opa.modules.configasset.model.ConfigAsset;
import jda.app.opasys.opa.modules.financeasset.model.FinanceAsset;
import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.app.opasys.opa.modules.planasset.model.PlanAsset;

@Repository
public interface FinanceAssetRepository extends PagingAndSortingRepository<FinanceAsset, Integer>{
	List<FinanceAsset> findByProjectId (int projectId);
	List<FinanceAsset> findByActivityId (int activityId);
}
