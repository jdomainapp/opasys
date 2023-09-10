package jda.app.opasys.hr.modules.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.hr.modules.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

}
