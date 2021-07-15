package warsito.musicweblibrary.repo;

import org.springframework.data.repository.CrudRepository;
import warsito.musicweblibrary.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
}
