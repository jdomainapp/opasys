package jda.app.opasys.project.modules.user.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import jda.app.opasys.project.modules.user.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

}
