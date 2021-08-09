package warsito.musicweblibrary.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Library;
import warsito.musicweblibrary.entity.User;

import java.util.List;

public interface LibraryRepository extends PagingAndSortingRepository<Library, Long> {
    Page<Library> findByUser(User user, Pageable pageable);
    Iterable<Library> findByRateBetween(int startRate, int endRate);
    Iterable<Library> findByAlbumContains(Album album);
}
