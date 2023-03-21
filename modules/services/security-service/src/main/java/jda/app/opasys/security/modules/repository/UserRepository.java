package jda.app.opasys.security.modules.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.security.modules.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

}
