package jda.app.opasys.finance.modules.financeasset.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.finance.modules.financeasset.model.FinanceAsset;

@Repository
public interface FinanceAssetRepository extends PagingAndSortingRepository<FinanceAsset, Integer>{
	List<FinanceAsset> findByProjectId (int projectId);
	List<FinanceAsset> findByActivityId (int activityId);
}
