package jda.app.opasys.issue.modules.user.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.issue.modules.issueasset.model.IssueAsset;
import jda.app.opasys.issue.modules.user.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>  {

}
