package waristo.musicweblibrary.repo;

import org.springframework.data.repository.CrudRepository;
import waristo.musicweblibrary.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
