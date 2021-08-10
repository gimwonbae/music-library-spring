package waristo.musicweblibrary.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import waristo.musicweblibrary.entity.Library;
import waristo.musicweblibrary.entity.User;

public interface LibraryRepository extends PagingAndSortingRepository<Library, Long> {
    Page<Library> findByUser(User user, Pageable pageable);
}
