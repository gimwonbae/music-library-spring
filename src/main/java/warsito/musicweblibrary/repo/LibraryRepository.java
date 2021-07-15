package warsito.musicweblibrary.repo;

import org.springframework.data.repository.CrudRepository;
import warsito.musicweblibrary.entity.Library;

public interface LibraryRepository extends CrudRepository<Library, Long> {
}
