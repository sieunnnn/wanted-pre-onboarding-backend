package wanted.pre_assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wanted.pre_assignment.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserById(Long userId);
}
